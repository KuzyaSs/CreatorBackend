package ru.ermakov.creator.service;

import ru.ermakov.creator.model.Subscription;
import ru.ermakov.creator.model.SubscriptionEntity;
import ru.ermakov.creator.model.SubscriptionRequest;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getSubscriptionsByCreatorId(String creatorId);

    Subscription getSubscriptionById(Long subscriptionId);

    void insertSubscription(SubscriptionRequest subscriptionRequest);

    void updateSubscription(Subscription subscription);

    void deleteSubscriptionById(Long subscriptionId);
}
