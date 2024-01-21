package ru.ermakov.creator.feature.creditGoal.model;

import ru.ermakov.creator.feature.creator.model.Creator;

import java.sql.Timestamp;

public record CreditGoal(
        Long id,
        Creator creator,
        Long targetBalance,
        Long balance,
        String description,
        Timestamp creationDate
) {
}
