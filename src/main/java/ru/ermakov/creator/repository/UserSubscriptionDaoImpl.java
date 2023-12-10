package ru.ermakov.creator.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.model.UserSubscriptionEntity;
import ru.ermakov.creator.model.UserSubscriptionRequest;
import ru.ermakov.creator.repository.mapper.UserSubscriptionRowMapper;

import java.time.LocalDateTime;
import java.util.List;

import static ru.ermakov.creator.repository.mapper.ParamNameConstants.*;

@Repository
public class UserSubscriptionDaoImpl implements UserSubscriptionDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserSubscriptionDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserSubscriptionEntity> getUserSubscriptionsByUserAndCreatorIds(String userId, String creatorId) {
        String query = """
                SELECT *
                FROM user_subscription
                WHERE subscription_id IN (
                    SELECT public.subscription.id
                    FROM public.subscription
                    WHERE creator_id = :creator_id
                )
                    AND user_id = :user_id
                    AND NOW() BETWEEN start_date AND end_date
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID_COLUMN, userId)
                .addValue(CREATOR_ID_COLUMN, creatorId);

        return jdbcTemplate.query(query, sqlParameterSource, new UserSubscriptionRowMapper());
    }

    @Override
    public Long getSubscriberCountBySubscriptionId(Long subscriptionId) {
        String query = """
                SELECT COUNT(*)
                FROM user_subscription
                WHERE subscription_id = :subscription_id
                    AND NOW() BETWEEN start_date AND end_date
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(SUBSCRIPTION_ID_COLUMN, subscriptionId);

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Long.class);
    }

    @Override
    public void insertUserSubscription(UserSubscriptionRequest userSubscriptionRequest) {
        String query = """
                INSERT INTO user_subscription (subscription_id, user_id, end_date)
                VALUES (:subscription_id, :user_id, :end_date)
                     """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(SUBSCRIPTION_ID_COLUMN, userSubscriptionRequest.subscriptionId())
                .addValue(USER_ID_COLUMN, userSubscriptionRequest.userId())
                .addValue(END_DATE_COLUMN, LocalDateTime.now().plusMonths(userSubscriptionRequest.durationInMonths()));

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void deleteUserSubscriptionById(Long userSubscriptionId) {
        String query = """
                UPDATE user_subscription
                SET end_date = NOW()
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, userSubscriptionId);

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public Boolean checkUserSubscriptionExistenceByUserAndSubscriptionIds(String userId, Long subscriptionId) {
        String query = """
                SELECT EXISTS(
                    SELECT 1
                    FROM user_subscription
                    WHERE user_id = :user_id
                        AND subscription_id = :subscription_id
                        AND NOW() BETWEEN start_date AND end_date
                )
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID_COLUMN, userId)
                .addValue(SUBSCRIPTION_ID_COLUMN, subscriptionId);

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Boolean.class);
    }
}
