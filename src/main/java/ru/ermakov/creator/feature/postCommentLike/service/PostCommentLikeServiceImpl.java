package ru.ermakov.creator.feature.postCommentLike.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.postCommentLike.model.PostCommentLikeRequest;
import ru.ermakov.creator.feature.postCommentLike.repository.PostCommentLikeDao;

@Service
public class PostCommentLikeServiceImpl implements PostCommentLikeService {
    private final PostCommentLikeDao postCommentLikeDao;

    public PostCommentLikeServiceImpl(PostCommentLikeDao postCommentLikeDao) {
        this.postCommentLikeDao = postCommentLikeDao;
    }

    @Override
    public Long getPostCommentLikeCountByPostCommentId(Long postCommentId) {
        return postCommentLikeDao.getPostCommentLikeCountByPostCommentId(postCommentId);
    }

    @Override
    public void insertPostCommentLike(PostCommentLikeRequest postCommentLikeRequest) {
        postCommentLikeDao.insertPostCommentLike(postCommentLikeRequest);
    }

    @Override
    public void deletePostCommentLike(PostCommentLikeRequest postCommentLikeRequest) {
        postCommentLikeDao.deletePostCommentLike(postCommentLikeRequest);

    }

    @Override
    public Boolean isLikedPostComment(PostCommentLikeRequest postCommentLikeRequest) {
        return postCommentLikeDao.isLikedPostComment(postCommentLikeRequest);
    }
}