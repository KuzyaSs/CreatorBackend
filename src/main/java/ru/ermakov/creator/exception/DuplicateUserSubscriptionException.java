package ru.ermakov.creator.exception;

public class DuplicateUserSubscriptionException extends RuntimeException {
    public DuplicateUserSubscriptionException() {
        super("User already has this subscription");
    }
}
