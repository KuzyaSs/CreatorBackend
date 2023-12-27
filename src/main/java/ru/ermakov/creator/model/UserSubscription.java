package ru.ermakov.creator.model;


import java.sql.Timestamp;

public record UserSubscription(
        Long id,
        Subscription subscription,
        User user,
        Timestamp startDate,
        Timestamp endDate
) {
}
