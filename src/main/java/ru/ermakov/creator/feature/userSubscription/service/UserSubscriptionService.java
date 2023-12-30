package ru.ermakov.creator.feature.userSubscription.service;

import ru.ermakov.creator.feature.userSubscription.model.UserSubscription;
import ru.ermakov.creator.feature.userSubscription.model.UserSubscriptionRequest;

import java.util.List;

public interface UserSubscriptionService {

    List<UserSubscription> getUserSubscriptionsByUserAndCreatorIds(String userId, String creatorId);

    Long getSubscriberCountBySubscriptionId(Long subscriptionId);

    Long getSubscriberCountByCreatorId(String creatorId);

    void insertUserSubscription(UserSubscriptionRequest userSubscriptionRequest);

    void deleteUserSubscriptionById(Long userSubscriptionId);
}
