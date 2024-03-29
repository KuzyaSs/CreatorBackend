package ru.ermakov.creator.feature.userSubscription.model;


import java.sql.Timestamp;

public record UserSubscriptionEntity(
        Long id,
        Long subscriptionId,
        String userId,
        Timestamp startDate,
        Timestamp endDate
) {
}
