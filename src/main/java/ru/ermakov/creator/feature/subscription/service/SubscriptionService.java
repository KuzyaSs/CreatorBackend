package ru.ermakov.creator.feature.subscription.service;

import ru.ermakov.creator.feature.subscription.model.Subscription;
import ru.ermakov.creator.feature.subscription.model.SubscriptionRequest;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getSubscriptionsByCreatorId(String creatorId);

    Subscription getSubscriptionById(Long subscriptionId);

    void insertSubscription(SubscriptionRequest subscriptionRequest);

    void updateSubscription(Long subscriptionId, SubscriptionRequest subscriptionRequest);

    void deleteSubscriptionById(Long subscriptionId);
}
