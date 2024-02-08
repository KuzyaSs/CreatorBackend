package ru.ermakov.creator.feature.post.repository;

import ru.ermakov.creator.feature.post.model.post.PostEntity;
import ru.ermakov.creator.feature.post.model.post.PostRequest;

import java.util.List;
import java.util.Optional;

public interface PostDao {
    List<PostEntity> getFilteredPostPageByUserId(Long postId, Integer limit);

    List<PostEntity> getFilteredFollowingPostPageByUserId(Long postId, Integer limit);

    List<PostEntity> getFilteredPostPageByUserAndCreatorIds(String creatorId, Long postId, Integer limit);

    List<PostEntity> getPostPageByUserIdAndSearchQuery(String searchQuery, Long postId, Integer limit);

    Optional<PostEntity> getPostByUserAndPostIds(Long postId);

    Long insertPost(PostRequest postRequest);

    void updatePost(Long postId, PostRequest postRequest);

    void deletePostById(Long postId);
}
