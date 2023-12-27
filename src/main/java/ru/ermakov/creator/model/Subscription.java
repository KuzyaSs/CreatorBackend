package ru.ermakov.creator.model;


public record Subscription(
        Long id,
        Creator creator,
        String title,
        String description,
        Long price
) {
}
