package ru.ermakov.creator.feature.postCommentLike.service;

import ru.ermakov.creator.feature.postCommentLike.model.PostCommentLikeRequest;

public interface PostCommentLikeService {
    Long getPostCommentLikeCountByPostCommentId(Long postCommentId);

    void insertPostCommentLike(PostCommentLikeRequest postCommentLikeRequest);

    void deletePostCommentLike(PostCommentLikeRequest postCommentLikeRequest);

    Boolean isLikedPostComment(PostCommentLikeRequest postCommentLikeRequest);
}
