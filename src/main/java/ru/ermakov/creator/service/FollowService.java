package ru.ermakov.creator.service;

import ru.ermakov.creator.model.Follow;
import ru.ermakov.creator.model.FollowRequest;

import java.util.List;

public interface FollowService {
    List<Follow> getFollowsByUserId(String userId);

    Follow getFollowByUserAndCreatorIds(FollowRequest followRequest);

    Long getFollowerCountByUserId(String userId);

    void insertFollow(FollowRequest followRequest);

    void deleteFollowById(Long followId);
}
