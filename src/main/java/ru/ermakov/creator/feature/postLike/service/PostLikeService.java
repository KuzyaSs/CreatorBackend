package ru.ermakov.creator.feature.postLike.service;

import ru.ermakov.creator.feature.postLike.model.LikeRequest;

public interface PostLikeService {
    Long getLikeCountByPostId(Long postId);

    void insertLike(LikeRequest likeRequest);

    void deleteLike(LikeRequest likeRequest);

    Boolean isLikedPost(LikeRequest likeRequest);
}
