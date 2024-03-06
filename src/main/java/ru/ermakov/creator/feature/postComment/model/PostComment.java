package ru.ermakov.creator.feature.postComment.model;

import ru.ermakov.creator.feature.user.model.User;

import java.sql.Timestamp;

public record PostComment(
        Long id,
        User user,
        Long postId,
        Long replyCommentId,
        String content,
        Timestamp publicationDate,
        Long likeCount,
        Boolean isLiked,
        Boolean isEdited
) {
}
