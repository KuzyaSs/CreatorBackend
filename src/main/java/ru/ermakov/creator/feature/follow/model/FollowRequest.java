package ru.ermakov.creator.feature.follow.model;


public record FollowRequest(
        String userId,
        String creatorId
) {
}
