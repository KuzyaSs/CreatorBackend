package ru.ermakov.creator.feature.post.repository;

import ru.ermakov.creator.feature.post.model.filter.BlogFilter;
import ru.ermakov.creator.feature.post.model.filter.FeedFilter;
import ru.ermakov.creator.feature.post.model.post.PostEntity;
import ru.ermakov.creator.feature.post.model.post.PostRequest;

import java.util.List;
import java.util.Optional;

public interface PostDao {
    List<PostEntity> getFilteredPostPage(FeedFilter feedFilter, Long postId, Integer limit);

    List<PostEntity> getFilteredPostPageByUserId(String userId, FeedFilter feedFilter, Long postId, Integer limit);

    List<PostEntity> getFilteredPostPageByCreatorId(String creatorId, BlogFilter blogFilter, Long postId, Integer limit);

    List<PostEntity> getPostPageBySearchQuery(String searchQuery, Long postId, Integer limit);

    Optional<PostEntity> getPostById(Long postId);

    Long insertPost(PostRequest postRequest);

    void updatePost(Long postId, PostRequest postRequest);

    void deletePostById(Long postId);
}
