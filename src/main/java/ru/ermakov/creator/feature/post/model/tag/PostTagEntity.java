package ru.ermakov.creator.feature.post.model.tag;

public record PostTagEntity(
        Long id,
        Long postId,
        Long tagId
) {
}
