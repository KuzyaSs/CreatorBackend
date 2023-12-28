package ru.ermakov.creator.feature.transaction.model;

import java.sql.Timestamp;

public record UserTransactionEntity(
        Long id,
        String senderUserId,
        String receiverUserId,
        Long transactionTypeId,
        Long amount,
        String message,
        Timestamp transactionDate
) {
}