package ru.ermakov.creator.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.model.UserSubscriptionEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.repository.mapper.ParamNameConstants.*;

public class UserSubscriptionRowMapper implements RowMapper<UserSubscriptionEntity> {
    @Override
    public UserSubscriptionEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserSubscriptionEntity(
                rs.getLong(ID_COLUMN),
                rs.getLong(SUBSCRIPTION_ID_COLUMN),
                rs.getString(USER_ID_COLUMN),
                rs.getTimestamp(START_DATE_COLUMN),
                rs.getTimestamp(END_DATE_COLUMN)
        );
    }
}
