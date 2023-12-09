package ru.ermakov.creator.service;

import ru.ermakov.creator.model.UserSubscription;
import ru.ermakov.creator.model.UserSubscriptionRequest;
import ru.ermakov.creator.repository.UserSubscriptionDao;

import java.util.List;

public class UserSubscriptionServiceImpl implements UserSubscriptionService {
    private final UserSubscriptionDao userSubscriptionDao;
    private final UserService userService;
    private final SubscriptionService subscriptionService;

    public UserSubscriptionServiceImpl(UserSubscriptionDao userSubscriptionDao, UserService userService, SubscriptionService subscriptionService) {
        this.userSubscriptionDao = userSubscriptionDao;
        this.userService = userService;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public List<UserSubscription> getUserSubscriptionsByUserAndCreatorIds(String userId, String creatorId) {
        return userSubscriptionDao.getUserSubscriptionsByUserAndCreatorIds(userId, creatorId)
                .stream()
                .map(userSubscriptionEntity ->
                        new UserSubscription(
                                userSubscriptionEntity.id(),
                                subscriptionService.getSubscriptionById(userSubscriptionEntity.subscriptionId()),
                                userService.getUserById(userSubscriptionEntity.userId()),
                                userSubscriptionEntity.startDate(),
                                userSubscriptionEntity.endDate()
                        )
                ).toList();
    }

    @Override
    public Long getSubscriberCountBySubscriptionId(Long subscriptionId) {
        return userSubscriptionDao.getSubscriberCountBySubscriptionId(subscriptionId);
    }

    @Override
    public void insertUserSubscription(UserSubscriptionRequest userSubscriptionRequest) {
        userSubscriptionDao.insertUserSubscription(userSubscriptionRequest);
    }

    @Override
    public void deleteUserSubscriptionById(Long userSubscriptionId) {
        userSubscriptionDao.deleteUserSubscriptionById(userSubscriptionId);
    }
}
