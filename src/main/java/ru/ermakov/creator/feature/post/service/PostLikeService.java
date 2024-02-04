package ru.ermakov.creator.feature.post.service;

import ru.ermakov.creator.feature.post.model.like.LikeRequest;

public interface PostLikeService {
    Long getLikeCountByPostId(Long postId);

    void insertLike(LikeRequest likeRequest);

    void deleteLike(LikeRequest likeRequest);
}
