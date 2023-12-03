package ru.ermakov.creator.model;


public record SubscriberRequest(
        Long subscriptionId,
        String userId,
        Integer durationInMonths
) {
}
