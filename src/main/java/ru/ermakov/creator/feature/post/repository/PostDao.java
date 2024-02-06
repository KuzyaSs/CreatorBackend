package ru.ermakov.creator.feature.post.repository;

import ru.ermakov.creator.feature.post.model.filter.BlogFilter;
import ru.ermakov.creator.feature.post.model.filter.FeedFilter;
import ru.ermakov.creator.feature.post.model.post.PostEntity;
import ru.ermakov.creator.feature.post.model.post.PostRequest;

import java.util.List;
import java.util.Optional;

public interface PostDao {
    List<PostEntity> getFilteredPostPageByUserId(String userId, FeedFilter feedFilter, Long postId, Integer limit);

    List<PostEntity> getFilteredFollowingPostPageByUserId(String userId, FeedFilter feedFilter, Long postId, Integer limit);

    List<PostEntity> getFilteredPostPageByUserAndCreatorIds(String userId, String creatorId, BlogFilter blogFilter, Long postId, Integer limit);

    List<PostEntity> getPostPageByUserIdAndSearchQuery(String userId, String searchQuery, Long postId, Integer limit);

    Optional<PostEntity> getPostByUserAndPostIds(String userId, Long postId);

    Long insertPost(PostRequest postRequest);

    void updatePost(Long postId, PostRequest postRequest);

    void deletePostById(Long postId);
}
