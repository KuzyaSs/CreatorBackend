package ru.ermakov.creator.exception;

public class DuplicateSubscriptionTitleException extends RuntimeException {
    public DuplicateSubscriptionTitleException() {
        super("Subscription with the same title already exists");
    }
}
