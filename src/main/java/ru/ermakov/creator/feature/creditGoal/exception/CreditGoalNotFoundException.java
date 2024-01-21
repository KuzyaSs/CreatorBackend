package ru.ermakov.creator.feature.creditGoal.exception;

public class CreditGoalNotFoundException extends RuntimeException {
    public CreditGoalNotFoundException() {
        super("Credit goal is not found");
    }
}
