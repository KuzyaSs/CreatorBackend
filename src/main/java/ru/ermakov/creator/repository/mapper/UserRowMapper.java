package ru.ermakov.creator.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.repository.ColumnNameConstants.*;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getString(ID_COLUMN),
                rs.getString(USERNAME_COLUMN),
                rs.getString(EMAIL_COLUMN),
                rs.getString(BIO_COLUMN),
                rs.getString(PROFILE_AVATAR_URL_COLUMN),
                rs.getString(PROFILE_BACKGROUND_URL_COLUMN),
                rs.getBoolean(IS_MODERATOR_COLUMN),
                rs.getTimestamp(REGISTRATION_DATE_COLUMN).toLocalDateTime().toLocalDate()
        );
    }
}