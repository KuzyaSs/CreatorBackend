package ru.ermakov.creator.feature.follow.model;


import java.sql.Timestamp;

public record FollowEntity(
        Long id,
        String userId,
        String creatorId,
        Timestamp startDate
) {
}
