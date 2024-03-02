package ru.ermakov.creator.feature.postComment.model;

import ru.ermakov.creator.feature.user.model.User;

import java.sql.Timestamp;

public record Comment(
        Long id,
        User user,
        Long postId,
        Long replyCommentId,
        String content,
        Timestamp publicationDate,
        Boolean isEdited
) {
}
