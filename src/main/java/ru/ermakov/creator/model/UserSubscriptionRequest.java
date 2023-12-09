package ru.ermakov.creator.model;


public record UserSubscriptionRequest(
        Long subscriptionId,
        String userId,
        Integer durationInMonths
) {
}
