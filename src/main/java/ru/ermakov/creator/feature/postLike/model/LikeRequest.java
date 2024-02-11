package ru.ermakov.creator.feature.postLike.model;

public record LikeRequest(
        String userId,
        Long postId
) {
}
