package ru.ermakov.creator.feature.subscription.model;


import ru.ermakov.creator.feature.creator.model.Creator;

public record Subscription(
        Long id,
        Creator creator,
        String title,
        String description,
        Long price
) {
}
