package ru.ermakov.creator.model;


import java.time.LocalDate;

public record UserSubscriptionEntity(
        Long id,
        Long subscriptionId,
        String userId,
        LocalDate startDate,
        LocalDate endDate
) {
}
