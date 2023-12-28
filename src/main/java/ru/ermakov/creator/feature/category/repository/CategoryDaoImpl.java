package ru.ermakov.creator.feature.category.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.category.model.Category;
import ru.ermakov.creator.feature.category.repository.mapper.CategoryRowMapper;

import java.util.List;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CategoryDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> getAllCategories() {
        String query = """
                SELECT id, name, FALSE AS is_selected
                FROM category
                    """;
        return jdbcTemplate.query(query, new CategoryRowMapper());
    }

    @Override
    public List<Category> getCategoriesByUserId(String userId) {
        String query = """
                SELECT category.id, category.name,
                    CASE WHEN category.id IN (
                        SELECT user_category.category_id
                        FROM user_category
                        WHERE user_category.user_id = :user_id
                        AND user_category.is_selected = TRUE) THEN TRUE
                        ELSE FALSE
                    END AS is_selected
                FROM category
                    """;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(USER_ID_COLUMN, userId);
        return jdbcTemplate.query(query, sqlParameterSource, new CategoryRowMapper());
    }

    @Override
    public void updateCategories(String userId, List<Category> categories) {
        String query = """
                INSERT INTO user_category
                VALUES (:user_id, :category_id, :is_selected)
                ON CONFLICT (user_id, category_id) DO UPDATE
                SET is_selected = excluded.is_selected
                    """;

        MapSqlParameterSource[] sqlParameterSources = categories.stream().map(category ->
                new MapSqlParameterSource()
                        .addValue(USER_ID_COLUMN, userId)
                        .addValue(CATEGORY_ID_COLUMN, category.id())
                        .addValue(IS_SELECTED_COLUMN, category.isSelected())
        ).toArray(MapSqlParameterSource[]::new);
        jdbcTemplate.batchUpdate(query, sqlParameterSources);
    }
}
