package ru.ermakov.creator.feature.user.model;

public record AuthUser(
        String id,
        String username,
        String email
) {
}