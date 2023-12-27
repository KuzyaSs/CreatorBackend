package ru.ermakov.creator.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.model.SubscriptionEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.repository.mapper.ParamNameConstants.*;

public class SubscriptionRowMapper implements RowMapper<SubscriptionEntity> {
    @Override
    public SubscriptionEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SubscriptionEntity(
                rs.getLong(ID_COLUMN),
                rs.getString(CREATOR_ID_COLUMN),
                rs.getString(TITLE_COLUMN),
                rs.getString(DESCRIPTION_COLUMN),
                rs.getLong(PRICE_COLUMN)
        );
    }
}
