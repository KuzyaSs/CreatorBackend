package ru.ermakov.creator.service;

import ru.ermakov.creator.exception.SubscriptionNotFoundException;
import ru.ermakov.creator.model.*;
import ru.ermakov.creator.repository.SubscriptionDao;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionDao subscriptionDao;
    private final CreatorService creatorService;

    public SubscriptionServiceImpl(SubscriptionDao subscriptionDao, CreatorService creatorService) {
        this.subscriptionDao = subscriptionDao;
        this.creatorService = creatorService;
    }

    @Override
    public List<Subscription> getSubscriptionsByCreatorId(String creatorId) {
        return subscriptionDao.getSubscriptionsByCreatorId(creatorId)
                .stream()
                .map(subscriptionEntity ->
                        new Subscription(
                                subscriptionEntity.id(),
                                creatorService.getCreatorByUserId(subscriptionEntity.creatorId()),
                                subscriptionEntity.title(),
                                subscriptionEntity.description(),
                                subscriptionEntity.price()
                        )
                ).toList();
    }

    @Override
    public Subscription getSubscriptionById(Long subscriptionId) {
        SubscriptionEntity subscriptionEntity = subscriptionDao.getSubscriptionById(subscriptionId)
                .orElseThrow(SubscriptionNotFoundException::new);
        return new Subscription(
                subscriptionEntity.id(),
                creatorService.getCreatorByUserId(subscriptionEntity.creatorId()),
                subscriptionEntity.title(),
                subscriptionEntity.description(),
                subscriptionEntity.price()
        );
    }

    @Override
    public void insertSubscription(SubscriptionRequest subscriptionRequest) {
        subscriptionDao.insertSubscription(subscriptionRequest);
    }

    @Override
    public void updateSubscription(Subscription subscription) {
        subscriptionDao.updateSubscription(subscription);

    }

    @Override
    public void deleteSubscriptionById(Long subscriptionId) {
        subscriptionDao.deleteSubscriptionById(subscriptionId);
    }
}
