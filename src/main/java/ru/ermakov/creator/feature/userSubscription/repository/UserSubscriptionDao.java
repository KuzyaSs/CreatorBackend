package ru.ermakov.creator.feature.userSubscription.repository;

import ru.ermakov.creator.feature.userSubscription.model.UserSubscriptionEntity;
import ru.ermakov.creator.feature.userSubscription.model.UserSubscriptionRequest;

import java.util.List;

public interface UserSubscriptionDao {
    List<UserSubscriptionEntity> getUserSubscriptionsByUserAndCreatorIds(String userId, String creatorId);

    Long getSubscriberCountBySubscriptionId(Long subscriptionId);

    Long getSubscriberCountByCreatorId(String creatorId);

    void insertUserSubscription(UserSubscriptionRequest userSubscriptionRequest);

    void deleteUserSubscriptionById(Long userSubscriptionId);

    Boolean userSubscriptionExistsByUserAndSubscriptionIds(String userId, Long subscriptionId);
}
