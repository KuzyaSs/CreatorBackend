package ru.ermakov.creator.feature.postLike.repository;

import ru.ermakov.creator.feature.postLike.model.LikeRequest;

public interface PostLikeDao {
    Long getLikeCountByPostId(Long postId);

    void insertLike(LikeRequest likeRequest);

    void deleteLike(LikeRequest likeRequest);

    Boolean isLikedPost(LikeRequest likeRequest);
}
