package ru.ermakov.creator.feature.postTag.repository;

import ru.ermakov.creator.feature.postTag.model.PostTagEntity;

import java.util.List;

public interface PostTagDao {
    List<PostTagEntity> getTagsByPostId(Long postId);

    void insertTagsByPostId(List<Long> tagIds, Long postId);

    void deleteTagsByPostId(Long postId);
}
