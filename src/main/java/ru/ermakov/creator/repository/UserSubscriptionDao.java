package ru.ermakov.creator.repository;

import ru.ermakov.creator.model.UserSubscriptionEntity;
import ru.ermakov.creator.model.UserSubscriptionRequest;

import java.util.List;

public interface UserSubscriptionDao {
    List<UserSubscriptionEntity> getUserSubscriptionsByUserAndCreatorIds(String userId, String creatorId);

    Long getSubscriberCountBySubscriptionId(Long subscriptionId);

    void insertUserSubscription(UserSubscriptionRequest userSubscriptionRequest);

    void deleteUserSubscriptionById(Long userSubscriptionId);

    Boolean checkUserSubscriptionExistenceByUserAndSubscriptionIds(String userId, Long subscriptionId);
}
