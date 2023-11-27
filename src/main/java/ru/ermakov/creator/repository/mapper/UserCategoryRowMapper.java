package ru.ermakov.creator.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.model.UserCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.repository.ColumnNameConstants.*;

public class UserCategoryRowMapper implements RowMapper<UserCategory> {
    @Override
    public UserCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserCategory(
                rs.getLong(ID_COLUMN),
                rs.getString(NAME_COLUMN),
                rs.getBoolean(IS_SELECTED_COLUMN)
        );
    }
}
