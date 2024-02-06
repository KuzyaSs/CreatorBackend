package ru.ermakov.creator.feature.post.model.post;

import ru.ermakov.creator.feature.creator.model.Creator;
import ru.ermakov.creator.feature.post.model.image.Image;
import ru.ermakov.creator.feature.subscription.model.Subscription;
import ru.ermakov.creator.feature.tag.model.Tag;

import java.sql.Timestamp;
import java.util.List;

public record Post(
        Long id,
        Creator creator,
        String title,
        String content,
        List<Image> images,
        List<Tag> tags,
        List<Subscription> requiredSubscriptions,
        Long likeCount,
        Timestamp publicationDate,
        Boolean isLiked,
        Boolean isEdited
) {
}
