package ru.ermakov.creator.feature.post.repository;

import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.post.model.like.LikeRequest;

@Repository
public class PostLikeDaoImpl implements PostLikeDao {
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
