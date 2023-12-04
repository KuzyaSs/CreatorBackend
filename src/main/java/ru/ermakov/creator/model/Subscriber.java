package ru.ermakov.creator.model;


import java.time.LocalDateTime;

public record Subscriber(
        Long id,
        Subscription subscription,
        String userId,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
