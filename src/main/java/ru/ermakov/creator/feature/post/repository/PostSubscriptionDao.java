package ru.ermakov.creator.feature.post.repository;

import ru.ermakov.creator.feature.post.model.subscription.PostSubscriptionEntity;
import ru.ermakov.creator.feature.subscription.model.Subscription;

import java.util.List;

public interface PostSubscriptionDao {
    List<PostSubscriptionEntity> getSubscriptionsByPostId(Long postId);

    void insertSubscriptionsByPostId(List<Subscription> subscriptions, Long postId);
}
