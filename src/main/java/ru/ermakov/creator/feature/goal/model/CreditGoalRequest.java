package ru.ermakov.creator.feature.goal.model;

public record CreditGoalRequest(
        String creatorId,
        Long targetBalance,
        String description
) {
}
