package ru.ermakov.creator.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.exception.FollowNotFoundException;
import ru.ermakov.creator.model.Follow;
import ru.ermakov.creator.model.FollowEntity;
import ru.ermakov.creator.model.FollowRequest;
import ru.ermakov.creator.repository.FollowDao;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    private final FollowDao followDao;
    private final UserService userService;

    public FollowServiceImpl(FollowDao followDao, UserService userService) {
        this.followDao = followDao;
        this.userService = userService;
    }

    @Override
    public List<Follow> getFollowsByUserId(String userId) {
        return followDao.getFollowsByUserId(userId)
                .stream()
                .map(followEntity -> new Follow(
                                followEntity.id(),
                                userService.getUserById(followEntity.userId()),
                                userService.getUserById(followEntity.creatorId()),
                                followEntity.startDate()
                        )
                ).toList();
    }

    @Override
    public Follow getFollowByUserAndCreatorIds(FollowRequest followRequest) {
        FollowEntity followEntity = followDao.getFollowByUserAndCreatorIds(followRequest)
                .orElseThrow(FollowNotFoundException::new);
        return new Follow(
                followEntity.id(),
                userService.getUserById(followEntity.userId()),
                userService.getUserById(followEntity.creatorId()),
                followEntity.startDate()
        );
    }

    @Override
    public Long getFollowerCountByUserId(String userId) {
        return followDao.getFollowerCountByUserId(userId);
    }

    @Override
    public void insertFollow(FollowRequest followRequest) {
        followDao.insertFollow(followRequest);
    }

    @Override
    public void deleteFollowById(Long followId) {
        followDao.deleteFollowById(followId);
    }
}
