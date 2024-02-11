package ru.ermakov.creator.feature.postImage.service;

import ru.ermakov.creator.feature.postImage.model.Image;

import java.util.List;

public interface PostImageService {
    List<Image> getImagesByPostId(Long postId);

    void insertImagesByPostId(List<String> imageUrls, Long postId);
}
