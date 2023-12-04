package ru.ermakov.creator.exception;

public class FollowNotFoundException extends RuntimeException {
    public FollowNotFoundException() {
        super("Follow is not found");
    }
}
