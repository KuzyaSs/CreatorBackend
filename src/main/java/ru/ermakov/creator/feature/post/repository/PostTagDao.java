package ru.ermakov.creator.feature.post.repository;

import ru.ermakov.creator.feature.post.model.tag.PostTagEntity;
import ru.ermakov.creator.feature.tag.model.Tag;

import java.util.List;

public interface PostTagDao {
    List<PostTagEntity> getTagsByPostId(Long postId);

    void insertTagsByPostId(List<Tag> tags, Long postId);

    void deleteTagsByPostId(Long postId);
}
