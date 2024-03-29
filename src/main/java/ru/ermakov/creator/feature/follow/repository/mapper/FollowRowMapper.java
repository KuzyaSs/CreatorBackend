package ru.ermakov.creator.feature.follow.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.feature.follow.model.FollowEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

public class FollowRowMapper implements RowMapper<FollowEntity> {
    @Override
    public FollowEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new FollowEntity(
                rs.getLong(ID_COLUMN),
                rs.getString(USER_ID_COLUMN),
                rs.getString(CREATOR_ID_COLUMN),
                rs.getTimestamp(START_DATE_COLUMN)
        );
    }
}
