package ru.ermakov.creator.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.model.Creator;
import ru.ermakov.creator.model.User;
import ru.ermakov.creator.repository.CategoryDao;
import ru.ermakov.creator.repository.FollowDao;

import java.util.List;

@Service
public class CreatorServiceImpl implements CreatorService {
    private final UserService userService;
    private final CategoryDao categoryDao;
    private final FollowDao followDao;

    // private final SubscriptionDao subscriptionDao;
    // private final PostDao postDao;

    public CreatorServiceImpl(UserService userService, CategoryDao categoryDao, FollowDao followDao) {
        this.userService = userService;
        this.categoryDao = categoryDao;
        this.followDao = followDao;
    }

    @Override
    public List<Creator> getCreatorsByPage(String searchQuery, Integer limit, Integer offset) {
        return userService.getUsersByPage(searchQuery, limit, offset)
                .stream()
                .map(user ->
                        getCreatorByUserId(user.getId())
                ).toList();
    }

    @Override
    public Creator getCreatorByUserId(String userId) {
        return new Creator(
                userService.getUserById(userId),
                categoryDao.getCategoriesByUserId(userId),
                followDao.getFollowerCountByUserId(userId),
                0L, // subscriptionDao.getSubscriptionCountByUserId(userId)
                0L // postDao.getPostCountByUserId(userId)
        );
    }
}
