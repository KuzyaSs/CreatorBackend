package ru.ermakov.creator.model;

import java.util.List;

public record Creator(
        User user,
        List<Category> categories,
        Long followerCount,
        Long subscriberCount,
        Long postCount
) {
}
