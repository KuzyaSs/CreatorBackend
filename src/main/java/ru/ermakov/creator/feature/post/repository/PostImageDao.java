package ru.ermakov.creator.feature.post.repository;

import ru.ermakov.creator.feature.post.model.image.PostImageEntity;

import java.util.List;

public interface PostImageDao {
    List<PostImageEntity> getImagesByPostId(Long postId);

    void insertImagesByPostId(List<String> imageUrls, Long postId);
    void deleteImagesByPostId(Long postId);
}
