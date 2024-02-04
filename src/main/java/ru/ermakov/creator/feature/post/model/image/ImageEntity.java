package ru.ermakov.creator.feature.post.model.image;

public record ImageEntity(
        Long id,
        Long postId,
        String url
) {
}
