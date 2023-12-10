package ru.ermakov.creator.exception;

public class DuplicateUsernameException extends RuntimeException {
    public DuplicateUsernameException() {
        super("This username is already in use");
    }
}
