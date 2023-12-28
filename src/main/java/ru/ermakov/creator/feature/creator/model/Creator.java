package ru.ermakov.creator.feature.creator.model;

import ru.ermakov.creator.feature.category.model.Category;
import ru.ermakov.creator.feature.user.model.User;

import java.util.List;

public record Creator(
        User user,
        List<Category> categories,
        Long followerCount,
        Long subscriberCount,
        Long postCount
) {
}
