package ru.ermakov.creator.model;


public record FollowRequest(
        String userId,
        String creatorId
) {
}
