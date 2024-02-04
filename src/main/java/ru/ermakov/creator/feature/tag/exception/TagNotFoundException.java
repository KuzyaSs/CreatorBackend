package ru.ermakov.creator.feature.tag.exception;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException() {
        super("Tag is not found");
    }
}
