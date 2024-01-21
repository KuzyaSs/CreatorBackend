package ru.ermakov.creator.feature.creditGoal.model;

public record CreditGoalRequest(
        String creatorId,
        Long targetBalance,
        String description
) {
}
