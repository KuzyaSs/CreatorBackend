package ru.ermakov.creator.feature.transaction.model;

public record CreditGoalTransactionRequest(
        Long creditGoalId,
        String senderUserId,
        String receiverUserId,
        Long transactionTypeId,
        Long amount,
        String message
) {
}