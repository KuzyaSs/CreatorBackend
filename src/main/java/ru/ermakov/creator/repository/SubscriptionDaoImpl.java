package ru.ermakov.creator.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.model.SubscriptionEntity;
import ru.ermakov.creator.model.SubscriptionRequest;
import ru.ermakov.creator.repository.mapper.SubscriptionRowMapper;

import java.util.List;
import java.util.Optional;

import static ru.ermakov.creator.repository.mapper.ParamNameConstants.*;

@Repository
public class SubscriptionDaoImpl implements SubscriptionDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SubscriptionDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SubscriptionEntity> getSubscriptionsByCreatorId(String creatorId) {
        String query = """
                SELECT *
                FROM public.subscription
                WHERE creator_id = :creator_id
                    AND is_deleted = FALSE
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(CREATOR_ID_COLUMN, creatorId);

        return jdbcTemplate.query(query, sqlParameterSource, new SubscriptionRowMapper());
    }

    @Override
    public Optional<SubscriptionEntity> getSubscriptionById(Long subscriptionId) {
        String query = """
                SELECT *
                FROM public.subscription
                WHERE id = :id
                    AND is_deleted = FALSE
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, subscriptionId);

        return jdbcTemplate.query(query, sqlParameterSource, new SubscriptionRowMapper())
                .stream()
                .findFirst();
    }

    @Override
    public void insertSubscription(SubscriptionRequest subscriptionRequest) {
        String query = """
                INSERT INTO public.subscription (creator_id, title, description, price)
                VALUES (:creator_id, :title, :description, :price)
                     """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(CREATOR_ID_COLUMN, subscriptionRequest.creatorId())
                .addValue(TITLE_COLUMN, subscriptionRequest.title())
                .addValue(DESCRIPTION_COLUMN, subscriptionRequest.description())
                .addValue(PRICE_COLUMN, subscriptionRequest.price());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void updateSubscription(Long subscriptionId, SubscriptionRequest subscriptionRequest) {
        String query = """
                UPDATE public.subscription
                SET title = :title,
                    description = :description,
                    price = :price
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, subscriptionId)
                .addValue(TITLE_COLUMN, subscriptionRequest.title())
                .addValue(DESCRIPTION_COLUMN, subscriptionRequest.description())
                .addValue(PRICE_COLUMN, subscriptionRequest.price());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void deleteSubscriptionById(Long subscriptionId) {
        String query = """
                UPDATE public.subscription
                SET is_deleted = TRUE
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, subscriptionId);

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public Boolean subscriptionExistsByTitle(Long subscriptionId, SubscriptionRequest subscriptionRequest) {
        String query = """
                SELECT EXISTS(
                    SELECT 1
                    FROM public.subscription
                    WHERE id != :id
                        AND title = :title
                        AND creator_id = :creator_id
                        AND is_deleted = FALSE
                )
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, subscriptionId)
                .addValue(TITLE_COLUMN, subscriptionRequest.title())
                .addValue(CREATOR_ID_COLUMN, subscriptionRequest.creatorId());

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Boolean.class);
    }
}
