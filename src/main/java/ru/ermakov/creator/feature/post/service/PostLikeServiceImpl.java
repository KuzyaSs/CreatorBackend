package ru.ermakov.creator.feature.post.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.post.model.like.LikeRequest;
import ru.ermakov.creator.feature.post.repository.PostLikeDao;

@Service
public class PostLikeServiceImpl implements PostLikeService {
    private final PostLikeDao postLikeDao;

    public PostLikeServiceImpl(PostLikeDao postLikeDao) {
        this.postLikeDao = postLikeDao;
    }

    @Override
    public Long getLikeCountByPostId(Long postId) {
        return postLikeDao.getLikeCountByPostId(postId);
    }

    @Override
    public void insertLike(LikeRequest likeRequest) {
        postLikeDao.insertLike(likeRequest);
    }

    @Override
    public void deleteLike(LikeRequest likeRequest) {
        postLikeDao.deleteLike(likeRequest);
    }

    @Override
    public Boolean isLikedPostByUserId(LikeRequest likeRequest) {
        return postLikeDao.isLikedPostByUserId(likeRequest);
    }
}
