package ru.ermakov.creator.feature.transaction.exception;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException() {
        super("Transaction is not found");
    }
}
