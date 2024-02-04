package ru.ermakov.creator.feature.post.model.filter;

import ru.ermakov.creator.feature.tag.model.Tag;

import java.util.List;

public record BlogFilter(
        String postType,
        List<Tag> tags
) {
}
