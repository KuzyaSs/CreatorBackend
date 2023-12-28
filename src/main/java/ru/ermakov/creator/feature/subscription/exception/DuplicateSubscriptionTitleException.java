package ru.ermakov.creator.feature.subscription.exception;

public class DuplicateSubscriptionTitleException extends RuntimeException {
    public DuplicateSubscriptionTitleException() {
        super("Subscription with the same title already exists");
    }
}
