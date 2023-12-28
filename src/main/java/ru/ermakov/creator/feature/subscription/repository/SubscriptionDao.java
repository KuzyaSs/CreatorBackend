package ru.ermakov.creator.feature.subscription.repository;

import ru.ermakov.creator.feature.subscription.model.SubscriptionEntity;
import ru.ermakov.creator.feature.subscription.model.SubscriptionRequest;

import java.util.List;
import java.util.Optional;

public interface SubscriptionDao {
    List<SubscriptionEntity> getSubscriptionsByCreatorId(String creatorId);
    Optional<SubscriptionEntity> getSubscriptionById(Long subscriptionId);

    void insertSubscription(SubscriptionRequest subscriptionRequest);

    void updateSubscription(Long subscriptionId, SubscriptionRequest subscriptionRequest);

    void deleteSubscriptionById(Long subscriptionId);

    Boolean subscriptionExistsByTitle(Long subscriptionId, SubscriptionRequest subscriptionRequest);
}
