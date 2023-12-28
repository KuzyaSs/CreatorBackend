package ru.ermakov.creator.feature.transaction.model;

import ru.ermakov.creator.feature.user.model.User;

import java.sql.Timestamp;

public record UserTransaction(
        Long id,
        User senderUser,
        User receiverUser,
        TransactionType transactionType,
        Long amount,
        String message,
        Timestamp transactionDate
) {
}