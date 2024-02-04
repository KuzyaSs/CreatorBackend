package ru.ermakov.creator.feature.tag.model;

public record TagEntity(
        Long id,
        String creatorId,
        String name) {
}
