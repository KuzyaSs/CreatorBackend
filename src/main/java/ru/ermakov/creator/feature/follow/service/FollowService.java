package ru.ermakov.creator.feature.follow.service;

import ru.ermakov.creator.feature.follow.model.Follow;
import ru.ermakov.creator.feature.follow.model.FollowRequest;

import java.util.List;

public interface FollowService {
    List<Follow> getFollowsByUserId(String userId);

    Follow getFollowByUserAndCreatorIds(FollowRequest followRequest);

    Long getFollowerCountByUserId(String userId);

    void insertFollow(FollowRequest followRequest);

    void deleteFollowById(Long followId);
}
