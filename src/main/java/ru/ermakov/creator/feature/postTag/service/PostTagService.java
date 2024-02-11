package ru.ermakov.creator.feature.postTag.service;

import ru.ermakov.creator.feature.tag.model.Tag;

import java.util.List;

public interface PostTagService {
    List<Tag> getTagsByPostId(Long postId);

    void insertTagsByPostId(List<Long> tagIds, Long postId);
}
