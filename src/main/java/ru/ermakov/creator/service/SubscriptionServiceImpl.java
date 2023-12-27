package ru.ermakov.creator.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.exception.SubscriptionNotFoundException;
import ru.ermakov.creator.exception.DuplicateSubscriptionTitleException;
import ru.ermakov.creator.model.*;
import ru.ermakov.creator.repository.SubscriptionDao;

import java.util.List;

@Service
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
        if (subscriptionDao.subscriptionExistsByTitle(-1L, subscriptionRequest)) {
            throw new DuplicateSubscriptionTitleException();
        }
        subscriptionDao.insertSubscription(subscriptionRequest);
    }

    @Override
    public void updateSubscription(Long subscriptionId, SubscriptionRequest subscriptionRequest) {
        if (subscriptionDao.subscriptionExistsByTitle(subscriptionId, subscriptionRequest)) {
            throw new DuplicateSubscriptionTitleException();
        }
        subscriptionDao.updateSubscription(subscriptionId, subscriptionRequest);
    }

    @Override
    public void deleteSubscriptionById(Long subscriptionId) {
        subscriptionDao.deleteSubscriptionById(subscriptionId);
    }
}
