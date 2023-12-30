package ru.ermakov.creator.feature.goal.exception;

public class GoalNotFoundException extends RuntimeException {
    public GoalNotFoundException() {
        super("Goal is not found");
    }
}
