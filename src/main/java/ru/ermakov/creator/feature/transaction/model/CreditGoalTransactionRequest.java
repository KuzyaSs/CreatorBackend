package ru.ermakov.creator.feature.transaction.model;

import java.sql.Timestamp;

public record CreditGoalTransactionRequest(
        Long creditGoalId,
        String userId,
        Long transactionTypeId,
        Long amount,
        String message
) {
}