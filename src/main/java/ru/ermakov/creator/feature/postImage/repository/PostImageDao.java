package ru.ermakov.creator.feature.postImage.repository;

import ru.ermakov.creator.feature.postImage.model.PostImageEntity;

import java.util.List;

public interface PostImageDao {
    List<PostImageEntity> getImagesByPostId(Long postId);

    void insertImagesByPostId(List<String> imageUrls, Long postId);
    void deleteImagesByPostId(Long postId);
}
