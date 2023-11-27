package ru.ermakov.creator.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.model.Category;
import ru.ermakov.creator.model.UserCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.repository.ColumnNameConstants.*;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Category(
                rs.getLong(ID_COLUMN),
                rs.getString(NAME_COLUMN)
        );
    }
}