package ru.ermakov.creator.feature.post.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.post.model.like.LikeRequest;

@Service
public class PostLikeServiceImpl implements PostLikeService {
    @Override
    public Long getLikeCountByPostId(Long postId) {
        return null;
    }

    @Override
    public void insertLike(LikeRequest likeRequest) {

    }

    @Override
    public void deleteLike(LikeRequest likeRequest) {

    }
}
