package ru.ermakov.creator.model;


public record FollowerRequest(
        String userId,
        String creatorId
) {
}
