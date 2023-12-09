package ru.ermakov.creator.model;

import java.time.LocalDate;

public record User(
        String id,
        String username,
        String email,
        String bio,
        String profileAvatarUrl,
        String profileBackgroundUrl,
        Boolean isModerator,
        LocalDate registrationDate
) {

}