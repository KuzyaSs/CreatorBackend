package ru.ermakov.creator.feature.postImage.model;

public record PostImageEntity(
        Long id,
        Long postId,
        String url
) {
}
