package ru.ermakov.creator.feature.post.model;

import java.util.List;

public record PostRequest(
        String creatorId,
        String title,
        String content,
        List<String> imageUrls,
        List<Long> tagIds,
        List<Long> requiredSubscriptionIds
) {
}
