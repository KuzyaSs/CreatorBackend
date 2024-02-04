package ru.ermakov.creator.feature.post.service;

import ru.ermakov.creator.feature.subscription.model.Subscription;

import java.util.List;

public interface PostSubscriptionService {
    List<Subscription> getSubscriptionsByPostId(Long postId);

    void insertSubscriptionsByPostId(List<Subscription> subscriptions, Long postId);
}
