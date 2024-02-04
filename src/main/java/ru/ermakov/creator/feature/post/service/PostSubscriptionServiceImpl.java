package ru.ermakov.creator.feature.post.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.subscription.model.Subscription;

import java.util.List;

@Service
public class PostSubscriptionServiceImpl implements PostSubscriptionService {
    @Override
    public List<Subscription> getSubscriptionsByPostId(Long postId) {
        return null;
    }

    @Override
    public void insertSubscriptionsByPostId(List<Subscription> subscriptions, Long postId) {

    }
}
