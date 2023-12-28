package ru.ermakov.creator.feature.subscription.exception;

public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException() {
        super("Subscription is not found");
    }
}
