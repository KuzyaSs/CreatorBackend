package ru.ermakov.creator.model;


public record SubscriptionRequest(
        String creatorId,
        String title,
        String description,
        Integer price
) {
}
