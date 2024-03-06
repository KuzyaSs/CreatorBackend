package ru.ermakov.creator.feature.postComment.model;

public record PostCommentRequest(
        String userId,
        Long postId,
        Long replyCommentId,
        String content
) {
}
