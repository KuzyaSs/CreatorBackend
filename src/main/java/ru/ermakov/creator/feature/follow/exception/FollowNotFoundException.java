package ru.ermakov.creator.feature.follow.exception;

public class FollowNotFoundException extends RuntimeException {
    public FollowNotFoundException() {
        super("Follow is not found");
    }
}
