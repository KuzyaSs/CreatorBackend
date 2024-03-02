package ru.ermakov.creator.feature.postComment.model;

public record CommentRequest(
        String userId,
        Long postId,
        Long replyCommentId,
        String content
) {
}
