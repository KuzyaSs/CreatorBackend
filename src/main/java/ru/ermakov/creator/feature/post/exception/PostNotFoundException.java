package ru.ermakov.creator.feature.post.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        super("Post is not found");
    }
}
