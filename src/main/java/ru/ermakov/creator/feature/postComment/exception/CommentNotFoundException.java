package ru.ermakov.creator.feature.postComment.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException() {
        super("Comment is not found");
    }
}
