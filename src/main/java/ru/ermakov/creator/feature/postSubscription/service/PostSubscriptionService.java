package ru.ermakov.creator.feature.postSubscription.service;

import ru.ermakov.creator.feature.subscription.model.Subscription;

import java.util.List;

public interface PostSubscriptionService {
    List<Subscription> getSubscriptionsByPostId(Long postId);

    void insertSubscriptionsByPostId(List<Long> subscriptionIds, Long postId);
}
