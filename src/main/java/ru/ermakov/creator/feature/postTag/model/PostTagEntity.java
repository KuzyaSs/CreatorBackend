package ru.ermakov.creator.feature.postTag.model;

public record PostTagEntity(
        Long id,
        Long postId,
        Long tagId
) {
}
