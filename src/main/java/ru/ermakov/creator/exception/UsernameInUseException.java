package ru.ermakov.creator.exception;

public class UsernameInUseException extends RuntimeException {
    public UsernameInUseException() {
        super("This username is already in use");
    }
}
