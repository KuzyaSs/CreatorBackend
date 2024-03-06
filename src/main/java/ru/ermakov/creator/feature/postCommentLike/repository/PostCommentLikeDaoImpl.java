package ru.ermakov.creator.feature.postCommentLike.repository;

import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.postCommentLike.model.PostCommentLikeRequest;

@Repository
public class PostCommentLikeDaoImpl implements PostCommentLikeDao {
    @Override
    public Long getPostCommentLikeCountByPostCommentId(Long postCommentId) {
        return null;
    }

    @Override
    public void insertPostCommentLike(PostCommentLikeRequest postCommentLikeRequest) {

    }

    @Override
    public void deletePostCommentLike(PostCommentLikeRequest postCommentLikeRequest) {

    }

    @Override
    public Boolean isLikedPostComment(PostCommentLikeRequest postCommentLikeRequest) {
        return null;
    }
}