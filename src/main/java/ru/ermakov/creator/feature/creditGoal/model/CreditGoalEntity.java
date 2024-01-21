package ru.ermakov.creator.feature.creditGoal.model;

import java.sql.Timestamp;

public record CreditGoalEntity(
        Long id,
        String creatorId,
        Long targetBalance,
        String description,
        Timestamp creationDate
) {
}
