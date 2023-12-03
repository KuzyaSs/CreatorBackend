package ru.ermakov.creator.model;


import java.time.LocalDateTime;

public record Follower(
        Long id,
        User user,
        User creator,
        LocalDateTime startDate
) {
}
