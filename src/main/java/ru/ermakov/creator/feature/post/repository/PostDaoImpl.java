package ru.ermakov.creator.feature.post.repository;

import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.post.model.filter.BlogFilter;
import ru.ermakov.creator.feature.post.model.filter.FeedFilter;
import ru.ermakov.creator.feature.post.model.post.PostEntity;
import ru.ermakov.creator.feature.post.model.post.PostRequest;

import java.util.List;
import java.util.Optional;

@Repository
public class PostDaoImpl implements PostDao {
    @Override
    public List<PostEntity> getFilteredPostPage(FeedFilter feedFilter, Long postId, Integer limit) {
        return null;
    }

    @Override
    public List<PostEntity> getFilteredPostPageByUserId(String userId, FeedFilter feedFilter, Long postId, Integer limit) {
        return null;
    }

    @Override
    public List<PostEntity> getFilteredPostPageByCreatorId(String creatorId, BlogFilter blogFilter, Long postId, Integer limit) {
        return null;
    }

    @Override
    public List<PostEntity> getPostPageBySearchQuery(String searchQuery, Long postId, Integer limit) {
        return null;
    }

    @Override
    public Optional<PostEntity> getPostById(Long postId) {
        return Optional.empty();
    }

    @Override
    public Long insertPost(PostRequest postRequest) {
        return null;
    }

    @Override
    public void updatePost(Long postId, PostRequest postRequest) {

    }

    @Override
    public void deletePostById(Long postId) {

    }
}
