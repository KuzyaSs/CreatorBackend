package ru.ermakov.creator.service;

import ru.ermakov.creator.model.UserSubscription;
import ru.ermakov.creator.model.UserSubscriptionRequest;

import java.util.List;

public interface UserSubscriptionService {

    List<UserSubscription> getUserSubscriptionsByUserAndCreatorIds(String userId, String creatorId);

    Long getSubscriberCountBySubscriptionId(Long subscriptionId);

    void insertUserSubscription(UserSubscriptionRequest userSubscriptionRequest);

    void deleteUserSubscriptionById(Long userSubscriptionId);
}
