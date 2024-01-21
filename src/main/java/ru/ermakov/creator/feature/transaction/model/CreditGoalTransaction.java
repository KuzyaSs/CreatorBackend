package ru.ermakov.creator.feature.transaction.model;

import ru.ermakov.creator.feature.creditGoal.model.CreditGoal;
import ru.ermakov.creator.feature.user.model.User;

import java.sql.Timestamp;

public record CreditGoalTransaction(
        Long id,
        CreditGoal creditGoal,
        User user,
        TransactionType transactionType,
        Long amount,
        String message,
        Timestamp transactionDate
) {
}