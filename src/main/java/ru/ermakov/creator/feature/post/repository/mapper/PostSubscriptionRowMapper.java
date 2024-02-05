package ru.ermakov.creator.feature.post.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.feature.post.model.subscription.PostSubscriptionEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

public class PostSubscriptionRowMapper implements RowMapper<PostSubscriptionEntity> {
    @Override
    public PostSubscriptionEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PostSubscriptionEntity(
                rs.getLong(ID_COLUMN),
                rs.getLong(POST_ID_COLUMN),
                rs.getLong(SUBSCRIPTION_ID_COLUMN)
        );
    }
}
