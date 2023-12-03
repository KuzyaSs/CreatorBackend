package ru.ermakov.creator.model;


public record Subscription(
        Long id,
        User user,
        String title,
        String description,
        Integer price
) {
}
