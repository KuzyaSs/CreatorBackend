package ru.ermakov.creator.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import ru.ermakov.creator.model.*;

import java.util.List;
import java.util.Optional;

import static ru.ermakov.creator.repository.mapper.ParamNameConstants.*;

public class SubscriptionDaoImpl implements SubscriptionDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SubscriptionDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SubscriptionEntity> getSubscriptionsByCreatorId(String creatorId) {
        return null;
    }

    @Override
    public Optional<SubscriptionEntity> getSubscriptionById(Long subscriptionId) {
        return Optional.empty();
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
    public void updateSubscription(Subscription subscription) {
        String query = """
                UPDATE public.subscription
                WHERE id = :id
                SET title = :title,
                    description = :description,
                    price = :price
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, subscription.id())
                .addValue(TITLE_COLUMN, subscription.title())
                .addValue(DESCRIPTION_COLUMN, subscription.description())
                .addValue(PRICE_COLUMN, subscription.price());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void deleteSubscriptionById(Long subscriptionId) {

    }
}
