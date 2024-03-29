package ru.ermakov.creator.feature.post.model;

import java.sql.Timestamp;

public record PostEntity(
        Long id,
        String creatorId,
        String title,
        String content,
        Timestamp publicationDate,
        Boolean isEdited
) {
}
