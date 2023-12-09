package ru.ermakov.creator.model;


public record SubscriptionEntity(
        Long id,
        String creatorId,
        String title,
        String description,
        Integer price
) {
}
