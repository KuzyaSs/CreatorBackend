package ru.ermakov.creator.exception;

public class UsernameInUseException extends RuntimeException {
    public UsernameInUseException(String message) {
        super(message);
    }
}
