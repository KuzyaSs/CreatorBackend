package ru.ermakov.creator.feature.postComment.model;

import java.sql.Timestamp;

public record PostCommentEntity(
        Long id,
        String userId,
        Long postId,
        Long replyCommentId,
        String content,
        Timestamp publicationDate,
        Boolean isEdited
) {
}
