package ru.ermakov.creator.feature.postCommentLike.repository;

import ru.ermakov.creator.feature.postCommentLike.model.PostCommentLikeRequest;

public interface PostCommentLikeDao {
    Long getPostCommentLikeCountByPostCommentId(Long postCommentId);

    void insertPostCommentLike(PostCommentLikeRequest postCommentLikeRequest);

    void deletePostCommentLike(PostCommentLikeRequest postCommentLikeRequest);

    Boolean isLikedPostComment(PostCommentLikeRequest postCommentLikeRequest);
}
