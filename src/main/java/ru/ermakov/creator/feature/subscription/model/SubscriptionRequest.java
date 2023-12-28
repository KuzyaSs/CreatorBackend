package ru.ermakov.creator.feature.subscription.model;


public record SubscriptionRequest(
        String creatorId,
        String title,
        String description,
        Long price
) {
}
