package ru.ermakov.creator.model;

public record AuthUser(
        String id,
        String username,
        String email
) {
}