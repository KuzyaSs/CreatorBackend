package ru.ermakov.creator.feature.postSubscription.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.postSubscription.model.PostSubscriptionEntity;
import ru.ermakov.creator.feature.postSubscription.repository.mapper.PostSubscriptionRowMapper;

import java.util.List;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.POST_ID_COLUMN;
import static ru.ermakov.creator.feature.shared.ParamNameConstants.SUBSCRIPTION_ID_COLUMN;

@Repository
public class PostSubscriptionDaoImpl implements PostSubscriptionDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PostSubscriptionDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PostSubscriptionEntity> getSubscriptionsByPostId(Long postId) {
        String query = """
                SELECT *
                FROM post_subscription
                WHERE post_id = :post_id
                ORDER BY subscription_id ASC
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(POST_ID_COLUMN, postId);

        return jdbcTemplate.query(query, sqlParameterSource, new PostSubscriptionRowMapper());
    }

    @Override
    public void insertSubscriptionsByPostId(List<Long> subscriptionIds, Long postId) {
        String query = """
                INSERT INTO post_subscription (post_id, subscription_id)
                VALUES (:post_id, :subscription_id)
                     """;

        SqlParameterSource[] sqlParameterSources = subscriptionIds
                .stream()
                .map(subscriptionId ->
                        new MapSqlParameterSource()
                                .addValue(POST_ID_COLUMN, postId)
                                .addValue(SUBSCRIPTION_ID_COLUMN, subscriptionId)
                ).toArray(SqlParameterSource[]::new);

        jdbcTemplate.batchUpdate(query, sqlParameterSources);
    }

    @Override
    public void deleteSubscriptionsByPostId(Long postId) {
        String query = """
                DELETE FROM post_subscription
                WHERE post_id = :post_id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(POST_ID_COLUMN, postId);

        jdbcTemplate.update(query, sqlParameterSource);
    }
}
