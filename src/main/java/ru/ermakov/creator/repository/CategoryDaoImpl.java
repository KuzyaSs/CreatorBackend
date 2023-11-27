package ru.ermakov.creator.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.model.Category;
import ru.ermakov.creator.model.UserCategory;
import ru.ermakov.creator.repository.mapper.CategoryRowMapper;
import ru.ermakov.creator.repository.mapper.UserCategoryRowMapper;

import java.util.List;

import static ru.ermakov.creator.repository.ColumnNameConstants.*;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CategoryDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> getAllCategories() {
        String query = """
                SELECT id, name
                FROM category
                """;
        return jdbcTemplate.query(query, new CategoryRowMapper());
    }

    @Override
    public List<UserCategory> getUserCategoriesByUserId(String userId) {
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
        return jdbcTemplate.query(query, sqlParameterSource, new UserCategoryRowMapper());
    }

    @Override
    public void updateUserCategories(String userId, List<UserCategory> userCategories) {
        String query = """
                INSERT INTO user_category
                VALUES (:user_id, :category_id, :is_selected)
                ON CONFLICT (user_id, category_id) DO UPDATE
                SET is_selected = excluded.is_selected
                """;

        MapSqlParameterSource[] sqlParameterSources = userCategories.stream().map(userCategory ->
                new MapSqlParameterSource()
                        .addValue(USER_ID_COLUMN, userId)
                        .addValue(CATEGORY_ID_COLUMN, userCategory.id())
                        .addValue(IS_SELECTED_COLUMN, userCategory.isSelected())
        ).toArray(MapSqlParameterSource[]::new);
        jdbcTemplate.batchUpdate(query, sqlParameterSources);
    }
}
