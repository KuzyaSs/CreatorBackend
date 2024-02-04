package ru.ermakov.creator.feature.post.repository;

import ru.ermakov.creator.feature.post.model.like.LikeRequest;

public interface PostLikeDao {
    Long getLikeCountByPostId(Long postId);

    void insertLike(LikeRequest likeRequest);

    void deleteLike(LikeRequest likeRequest);
}
