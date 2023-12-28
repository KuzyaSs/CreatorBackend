package ru.ermakov.creator.feature.subscription.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.creator.service.CreatorService;
import ru.ermakov.creator.feature.subscription.exception.SubscriptionNotFoundException;
import ru.ermakov.creator.feature.subscription.exception.DuplicateSubscriptionTitleException;
import ru.ermakov.creator.feature.subscription.model.Subscription;
import ru.ermakov.creator.feature.subscription.model.SubscriptionEntity;
import ru.ermakov.creator.feature.subscription.model.SubscriptionRequest;
import ru.ermakov.creator.feature.subscription.repository.SubscriptionDao;

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
