package ru.ermakov.creator.feature.post.service;

import ru.ermakov.creator.feature.tag.model.Tag;

import java.util.List;

public interface PostTagService {
    List<Tag> getTagsByPostId(Long postId);

    void insertTagsByPostId(List<Tag> tags, Long postId);
}
