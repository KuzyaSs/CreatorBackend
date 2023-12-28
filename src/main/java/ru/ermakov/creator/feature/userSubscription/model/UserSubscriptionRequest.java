package ru.ermakov.creator.feature.userSubscription.model;


public record UserSubscriptionRequest(
        Long subscriptionId,
        String userId,
        Integer durationInMonths
) {
}
