package ru.ermakov.creator.model;


import java.time.LocalDate;

public record FollowEntity(
        Long id,
        String userId,
        String creatorId,
        LocalDate startDate
) {
}
