package ru.ermakov.creator.feature.post.model.image;

public record PostImageEntity(
        Long id,
        Long postId,
        String url
) {
}
