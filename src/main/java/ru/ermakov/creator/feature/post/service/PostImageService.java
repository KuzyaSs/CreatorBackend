package ru.ermakov.creator.feature.post.service;

import ru.ermakov.creator.feature.post.model.image.Image;

import java.util.List;

public interface PostImageService {
    List<Image> getImagesByPostId(Long postId);

    void insertImagesByPostId(List<String> imageUrls, Long postId);
}
