package ru.ermakov.creator.feature.post.model.like;

public record LikeRequest(
        String userId,
        Long postId
) {
}
