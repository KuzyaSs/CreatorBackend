package ru.ermakov.creator.feature.user.model;

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