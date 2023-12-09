package ru.ermakov.creator.repository;

import ru.ermakov.creator.model.Subscription;
import ru.ermakov.creator.model.SubscriptionEntity;
import ru.ermakov.creator.model.SubscriptionRequest;

import java.util.List;
import java.util.Optional;

public interface SubscriptionDao {
    List<SubscriptionEntity> getSubscriptionsByCreatorId(String creatorId);
    Optional<SubscriptionEntity> getSubscriptionById(Long subscriptionId);

    void insertSubscription(SubscriptionRequest subscriptionRequest);

    void updateSubscription(Subscription subscription);

    void deleteSubscriptionById(Long subscriptionId);
}
