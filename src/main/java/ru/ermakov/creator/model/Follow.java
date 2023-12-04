package ru.ermakov.creator.model;


import java.time.LocalDate;

public record Follow(
        Long id,
        User user,
        User creator,
        LocalDate startDate
) {
}
