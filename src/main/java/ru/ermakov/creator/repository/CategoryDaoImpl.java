package ru.ermakov.creator.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.model.Category;
import ru.ermakov.creator.repository.mapper.CategoryRowMapper;

import java.util.List;

import static ru.ermakov.creator.repository.ColumnNameConstants.USER_ID_COLUMN;

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
    public List<Category> getUserCategories(String userId) {
        String query = """
                SELECT category.id, category.name,
                    CASE WHEN category.id IN (
                        SELECT user_category.category_id
                        FROM user_category
                        WHERE user_category.user_id = :user_id) THEN TRUE
                        ELSE FALSE
                    END AS is_selected
                FROM category
                """;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(USER_ID_COLUMN, userId);
        return jdbcTemplate.query(query, sqlParameterSource, new CategoryRowMapper());
    }
}
