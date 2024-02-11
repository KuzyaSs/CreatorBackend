package ru.ermakov.creator.feature.postSubscription.model;

public record PostSubscriptionEntity(
        Long id,
        Long postId,
        Long subscriptionId
) {
}
