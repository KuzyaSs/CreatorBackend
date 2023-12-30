package ru.ermakov.creator.feature.transaction.exception;

public class InsufficientFundsInGoalException extends RuntimeException {
    public InsufficientFundsInGoalException() {
        super("Insufficient funds in the goal");
    }
}
