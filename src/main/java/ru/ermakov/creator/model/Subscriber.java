package ru.ermakov.creator.model;


import java.time.LocalDateTime;

public record Subscriber(
        Long id,
        Subscription subscription,
        User user,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
