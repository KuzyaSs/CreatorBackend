package ru.ermakov.creator.feature.post.repository;

import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.post.model.subscription.PostSubscriptionEntity;
import ru.ermakov.creator.feature.subscription.model.Subscription;

import java.util.List;

@Repository
public class PostSubscriptionDaoImpl implements PostSubscriptionDao {
    @Override
    public List<PostSubscriptionEntity> getSubscriptionsByPostId(Long postId) {
        return null;
    }

    @Override
    public void insertSubscriptionsByPostId(List<Subscription> subscriptions, Long postId) {

    }
}
