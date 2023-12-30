package ru.ermakov.creator.feature.transaction.exception;

public class InsufficientFundsInAccountException extends RuntimeException {
    public InsufficientFundsInAccountException() {
        super("Insufficient funds in the account");
    }
}
