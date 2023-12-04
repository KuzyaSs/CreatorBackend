package ru.ermakov.creator.model;


public record Subscription(
        Long id,
        String userId,
        String title,
        String description,
        Integer price
) {
}
