package ru.ermakov.creator.feature.post.model.post;

import ru.ermakov.creator.feature.subscription.model.Subscription;
import ru.ermakov.creator.feature.tag.model.Tag;

import java.util.List;

public record PostRequest(
        String creatorId,
        String title,
        String content,
        List<String> imageUrls,
        List<Tag> tags,
        List<Subscription> requiredSubscriptions
) {
}
