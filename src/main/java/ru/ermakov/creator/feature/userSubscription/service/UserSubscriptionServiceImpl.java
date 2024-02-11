package ru.ermakov.creator.feature.userSubscription.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.subscription.model.Subscription;
import ru.ermakov.creator.feature.subscription.service.SubscriptionService;
import ru.ermakov.creator.feature.transaction.model.UserTransactionRequest;
import ru.ermakov.creator.feature.transaction.service.TransactionService;
import ru.ermakov.creator.feature.user.service.UserService;
import ru.ermakov.creator.feature.userSubscription.exception.DuplicateUserSubscriptionException;
import ru.ermakov.creator.feature.userSubscription.repository.UserSubscriptionDao;
import ru.ermakov.creator.feature.userSubscription.model.UserSubscription;
import ru.ermakov.creator.feature.userSubscription.model.UserSubscriptionRequest;

import java.util.List;

@Service
public class UserSubscriptionServiceImpl implements UserSubscriptionService {
    private final UserSubscriptionDao userSubscriptionDao;
    private final UserService userService;
    private final SubscriptionService subscriptionService;
    private final TransactionService transactionService;

    private static final Long BUY_SUBSCRIPTION_TRANSACTION_ID = 4L;

    public UserSubscriptionServiceImpl(
            UserSubscriptionDao userSubscriptionDao,
            UserService userService,
            SubscriptionService subscriptionService,
            TransactionService transactionService) {
        this.userSubscriptionDao = userSubscriptionDao;
        this.userService = userService;
        this.subscriptionService = subscriptionService;
        this.transactionService = transactionService;
    }

    @Override
    public List<UserSubscription> getUserSubscriptionsByUserId(String userId) {
        return userSubscriptionDao.getUserSubscriptionsByUserId(userId)
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
    public Long getSubscriberCountByCreatorId(String creatorId) {
        return userSubscriptionDao.getSubscriberCountByCreatorId(creatorId);
    }

    @Override
    public Boolean isUserSubscribedBySubscriptionIds(String userId, List<Long> subscriptionIds) {
        return userSubscriptionDao.isUserSubscribedBySubscriptionIds(userId, subscriptionIds);
    }

    @Override
    public void insertUserSubscription(UserSubscriptionRequest userSubscriptionRequest) {
        // Check that a user exists.
        userService.getUserById(userSubscriptionRequest.userId());

        // Check that a subscription exists.
        Subscription subscription = subscriptionService.getSubscriptionById(userSubscriptionRequest.subscriptionId());

        // Check that user doesn't have a subscription of this creator.
        if (userSubscriptionDao.userSubscriptionExistsByUserAndSubscriptionIds(userSubscriptionRequest.userId(), userSubscriptionRequest.subscriptionId())) {
            throw new DuplicateUserSubscriptionException();
        }

        // Create transaction.
        UserTransactionRequest userTransactionRequest = new UserTransactionRequest(
                userSubscriptionRequest.userId(),
                subscription.creator().user().id(),
                BUY_SUBSCRIPTION_TRANSACTION_ID,
                subscription.price() * userSubscriptionRequest.durationInMonths(),
                ""
        );
        transactionService.insertUserTransaction(userTransactionRequest);

        userSubscriptionDao.insertUserSubscription(userSubscriptionRequest);
    }

    @Override
    public void deleteUserSubscriptionById(Long userSubscriptionId) {
        userSubscriptionDao.deleteUserSubscriptionById(userSubscriptionId);
    }
}
