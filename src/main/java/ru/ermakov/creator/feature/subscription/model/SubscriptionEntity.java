package ru.ermakov.creator.feature.subscription.model;


public record SubscriptionEntity(
        Long id,
        String creatorId,
        String title,
        String description,
        Long price
) {
}
