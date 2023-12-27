package ru.ermakov.creator.model;

import java.sql.Timestamp;

public record User(
        String id,
        String username,
        String email,
        String bio,
        String profileAvatarUrl,
        String profileBackgroundUrl,
        Boolean isModerator,
        Timestamp registrationDate
) {

}