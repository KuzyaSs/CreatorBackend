package ru.ermakov.creator.feature.post.model.subscription;

public record PostSubscriptionEntity(
        Long id,
        Long postId,
        Long subscriptionId
) {
}
