package ru.ermakov.creator.feature.transaction.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Insufficient funds in the account");
    }
}
