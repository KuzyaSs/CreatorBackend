package ru.ermakov.creator.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.repository.Constants.*;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
                rs.getLong(ID_COLUMN),
                rs.getString(USERNAME_COLUMN),
                rs.getString(EMAIL_COLUMN),
                rs.getString(ABOUT_COLUMN),
                rs.getString(PROFILE_AVATAR_URL_COLUMN),
                rs.getString(BACKGROUND_AVATAR_URL_COLUMN),
                rs.getTimestamp(REGISTRATION_DATE_COLUMN).toLocalDateTime().toLocalDate()
        );
    }
}
