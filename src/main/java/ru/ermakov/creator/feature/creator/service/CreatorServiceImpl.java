package ru.ermakov.creator.feature.creator.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.category.service.CategoryService;
import ru.ermakov.creator.feature.creator.model.Creator;
import ru.ermakov.creator.feature.follow.repository.FollowDao;
import ru.ermakov.creator.feature.user.service.UserService;
import ru.ermakov.creator.feature.userSubscription.repository.UserSubscriptionDao;
import ru.ermakov.creator.feature.userSubscription.service.UserSubscriptionService;

import java.util.List;

@Service
public class CreatorServiceImpl implements CreatorService {
    private final UserService userService;
    private final CategoryService categoryService;
    private final FollowDao followDao;
    private final UserSubscriptionDao userSubscriptionDao;
    // private final PostService postService;

    public CreatorServiceImpl(UserService userService, CategoryService categoryService, FollowDao followDao, UserSubscriptionDao userSubscriptionDao) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.followDao = followDao;
        this.userSubscriptionDao = userSubscriptionDao;
    }

    @Override
    public List<Creator> getCreatorsByPage(String searchQuery, Integer limit, Integer offset) {
        return userService.getUsersByPage(searchQuery, limit, offset)
                .stream()
                .map(user ->
                        getCreatorByUserId(user.id())
                ).toList();
    }

    @Override
    public Creator getCreatorByUserId(String userId) {
        return new Creator(
                userService.getUserById(userId),
                categoryService.getCategoriesByUserId(userId),
                followDao.getFollowerCountByUserId(userId),
                userSubscriptionDao.getSubscriberCountByCreatorId(userId),
                0L // postService.getPostCountByUserId(userId)
        );
    }
}
