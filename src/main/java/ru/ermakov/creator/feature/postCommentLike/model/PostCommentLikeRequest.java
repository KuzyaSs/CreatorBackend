package ru.ermakov.creator.feature.postCommentLike.model;

public record PostCommentLikeRequest(
        String userId,
        Long postCommentId
) {
}