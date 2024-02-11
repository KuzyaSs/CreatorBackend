package ru.ermakov.creator.feature.postLike.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.postLike.model.LikeRequest;
import ru.ermakov.creator.feature.postLike.repository.PostLikeDao;

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
        if (!isLikedPost(likeRequest)) {
            postLikeDao.insertLike(likeRequest);
        }
    }

    @Override
    public void deleteLike(LikeRequest likeRequest) {
        postLikeDao.deleteLike(likeRequest);
    }

    @Override
    public Boolean isLikedPost(LikeRequest likeRequest) {
        return postLikeDao.isLikedPost(likeRequest);
    }
}
