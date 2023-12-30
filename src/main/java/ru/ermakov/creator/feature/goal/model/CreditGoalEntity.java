package ru.ermakov.creator.feature.goal.model;

import java.sql.Timestamp;

public record CreditGoalEntity(
        Long id,
        String creatorId,
        Long targetBalance,
        String description,
        Timestamp creationDate
) {
}
