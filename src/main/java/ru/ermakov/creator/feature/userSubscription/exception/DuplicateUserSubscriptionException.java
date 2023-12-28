package ru.ermakov.creator.feature.userSubscription.exception;

public class DuplicateUserSubscriptionException extends RuntimeException {
    public DuplicateUserSubscriptionException() {
        super("User already has this subscription");
    }
}
