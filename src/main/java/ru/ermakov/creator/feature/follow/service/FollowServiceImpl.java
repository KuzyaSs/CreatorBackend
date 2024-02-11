package ru.ermakov.creator.feature.follow.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.creator.service.CreatorService;
import ru.ermakov.creator.feature.follow.exception.FollowNotFoundException;
import ru.ermakov.creator.feature.follow.repository.FollowDao;
import ru.ermakov.creator.feature.user.service.UserService;
import ru.ermakov.creator.feature.follow.model.Follow;
import ru.ermakov.creator.feature.follow.model.FollowEntity;
import ru.ermakov.creator.feature.follow.model.FollowRequest;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {
    private final FollowDao followDao;
    private final UserService userService;
    private final CreatorService creatorService;

    public FollowServiceImpl(FollowDao followDao, UserService userService, CreatorService creatorService) {
        this.followDao = followDao;
        this.userService = userService;
        this.creatorService = creatorService;
    }

    @Override
    public List<Follow> getFollowPageBySearchQueryAndUserId(
            String searchQuery,
            Integer limit,
            Integer offset,
            String userId
    ) {
        return followDao.getFollowPageBySearchQueryAndUserId(searchQuery, limit, offset, userId)
                .stream()
                .map(followEntity ->
                        new Follow(
                                followEntity.id(),
                                userService.getUserById(followEntity.userId()),
                                creatorService.getCreatorByUserId(followEntity.creatorId()),
                                followEntity.startDate()
                        )
                ).toList();
    }

    @Override
    public List<Follow> getFollowsByUserId(String userId) {
        return followDao.getFollowsByUserId(userId)
                .stream()
                .map(followEntity ->
                        new Follow(
                                followEntity.id(),
                                userService.getUserById(followEntity.userId()),
                                creatorService.getCreatorByUserId(followEntity.creatorId()),
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
                creatorService.getCreatorByUserId(followEntity.creatorId()),
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
