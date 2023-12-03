package ru.ermakov.creator.repository;

import ru.ermakov.creator.model.Follower;
import ru.ermakov.creator.model.FollowerRequest;

import java.util.List;
import java.util.Optional;

public interface FollowerDao {
    Optional<Follower> getFollowerByUserAndCreatorIds(FollowerRequest followerRequest);
    List<Follower> getFollowersByUserId(String creatorId);
    Long getNumFollowersByCreatorId(String creatorId);
    void insertFollower(FollowerRequest followerRequest);
    void deleteFollowerById(Long followerId);
}
