package ru.ermakov.creator.model;


import java.time.LocalDate;

public record UserSubscription(
        Long id,
        Subscription subscription,
        User user,
        LocalDate startDate,
        LocalDate endDate
) {
}
