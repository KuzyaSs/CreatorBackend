package ru.ermakov.creator.feature.post.repository;

import ru.ermakov.creator.feature.post.model.image.ImageEntity;

import java.util.List;

public interface PostImageDao {
    List<ImageEntity> getImagesByPostId(Long postId);

    void insertImagesByPostId(List<String> imageUrls, Long postId);
}
