package ru.ermakov.creator.feature.postSubscription.repository;

import ru.ermakov.creator.feature.postSubscription.model.PostSubscriptionEntity;

import java.util.List;

public interface PostSubscriptionDao {
    List<PostSubscriptionEntity> getSubscriptionsByPostId(Long postId);

    void insertSubscriptionsByPostId(List<Long> subscriptionIds, Long postId);

    void deleteSubscriptionsByPostId(Long postId);
}
