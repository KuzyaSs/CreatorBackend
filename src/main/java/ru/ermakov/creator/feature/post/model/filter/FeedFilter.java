package ru.ermakov.creator.feature.post.model.filter;

import ru.ermakov.creator.feature.category.model.Category;

import java.util.List;

public record FeedFilter(
        String postType,
        List<Category> categories
) {
}
