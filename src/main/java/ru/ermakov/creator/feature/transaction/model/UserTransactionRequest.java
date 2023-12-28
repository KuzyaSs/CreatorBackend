package ru.ermakov.creator.feature.transaction.model;

public record UserTransactionRequest(
        String senderUserId,
        String receiverUserId,
        Long transactionTypeId,
        Long amount,
        String message
) {
}