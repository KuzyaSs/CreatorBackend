package ru.ermakov.creator.model;


import java.sql.Timestamp;

public record Follow(
        Long id,
        User user,
        Creator creator,
        Timestamp startDate
) {
}
