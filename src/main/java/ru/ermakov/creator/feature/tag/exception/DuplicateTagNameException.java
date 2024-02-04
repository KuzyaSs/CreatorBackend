package ru.ermakov.creator.feature.tag.exception;

public class DuplicateTagNameException extends RuntimeException {
    public DuplicateTagNameException() {
        super("Tag with the same name already exists");
    }
}
