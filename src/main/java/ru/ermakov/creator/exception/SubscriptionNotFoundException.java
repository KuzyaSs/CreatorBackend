package ru.ermakov.creator.exception;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException() {
        super("Subscription is not found");
    }
}
