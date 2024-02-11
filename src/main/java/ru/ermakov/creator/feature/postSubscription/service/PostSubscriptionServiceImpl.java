package ru.ermakov.creator.feature.postSubscription.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.postSubscription.model.PostSubscriptionEntity;
import ru.ermakov.creator.feature.postSubscription.repository.PostSubscriptionDao;
import ru.ermakov.creator.feature.subscription.model.Subscription;
import ru.ermakov.creator.feature.subscription.service.SubscriptionService;

import java.util.List;

@Service
public class PostSubscriptionServiceImpl implements PostSubscriptionService {
    private final PostSubscriptionDao postSubscriptionDao;
    private final SubscriptionService subscriptionService;

    public PostSubscriptionServiceImpl(PostSubscriptionDao postSubscriptionDao, SubscriptionService subscriptionService) {
        this.postSubscriptionDao = postSubscriptionDao;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public List<Subscription> getSubscriptionsByPostId(Long postId) {
        List<PostSubscriptionEntity> postSubscriptionEntities = postSubscriptionDao.getSubscriptionsByPostId(postId);

        return postSubscriptionEntities
                .stream()
                .map(postSubscriptionEntity ->
                        subscriptionService.getSubscriptionById(postSubscriptionEntity.subscriptionId())
                ).toList();
    }

    @Override
    public void insertSubscriptionsByPostId(List<Long> subscriptionIds, Long postId) {
        postSubscriptionDao.deleteSubscriptionsByPostId(postId);
        postSubscriptionDao.insertSubscriptionsByPostId(subscriptionIds, postId);
    }
}
