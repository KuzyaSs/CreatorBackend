package ru.ermakov.creator.feature.category.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.feature.category.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Category(
                rs.getLong(ID_COLUMN),
                rs.getString(NAME_COLUMN),
                rs.getBoolean(IS_SELECTED_COLUMN)
        );
    }
}
