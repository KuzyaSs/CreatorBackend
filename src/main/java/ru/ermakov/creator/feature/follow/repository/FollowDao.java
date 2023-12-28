package ru.ermakov.creator.feature.follow.repository;

import ru.ermakov.creator.feature.follow.model.FollowEntity;
import ru.ermakov.creator.feature.follow.model.FollowRequest;

import java.util.List;
import java.util.Optional;

public interface FollowDao {
    List<FollowEntity> getFollowsByUserId(String userId);

    Optional<FollowEntity> getFollowByUserAndCreatorIds(FollowRequest followRequest);

    Long getFollowerCountByUserId(String userId);

    void insertFollow(FollowRequest followRequest);

    void deleteFollowById(Long followerId);
}
