package ru.ermakov.creator.repository;

import ru.ermakov.creator.model.UserSubscriptionEntity;
import ru.ermakov.creator.model.UserSubscriptionRequest;

import java.util.List;

public class UserSubscriptionDaoImpl implements UserSubscriptionDao {
    @Override
    public List<UserSubscriptionEntity> getUserSubscriptionsByUserAndCreatorIds(String userId, String creatorId) {
        return null;
    }

    @Override
    public Long getSubscriberCountBySubscriptionId(Long userSubscriptionId) {
        return null;
    }

    @Override
    public void insertUserSubscription(UserSubscriptionRequest userSubscriptionRequest) {

    }

    @Override
    public void deleteUserSubscriptionById(Long userSubscriptionId) {

    }
}
