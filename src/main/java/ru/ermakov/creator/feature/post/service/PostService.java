package ru.ermakov.creator.feature.post.service;

import ru.ermakov.creator.feature.postLike.model.LikeRequest;
import ru.ermakov.creator.feature.post.model.Post;
import ru.ermakov.creator.feature.post.model.PostRequest;

import java.util.List;

public interface PostService {
    // In the discover screen.
    List<Post> getFilteredPostPageByUserId(
            String userId,
            String postType,
            List<Long> categoryIds,
            Long postId,
            Integer limit
    );

    // In the following screen.
    List<Post> getFilteredFollowingPostPageByUserId(
            String userId,
            String postType,
            List<Long> categoryIds,
            Long postId,
            Integer limit
    );

    // In the blog screen.
    List<Post> getFilteredPostPageByUserAndCreatorIds(
            String userId,
            String creatorId,
            String postType,
            List<Long> tagIds,
            Long postId,
            Integer limit
    );

    // In the search screen.
    List<Post> getPostPageByUserIdAndSearchQuery(String userId, String searchQuery, Long postId, Integer limit);

    Post getPostByUserAndPostIds(String userId, Long postId);

    Long getPostCountByCreatorId(String creatorId);

    void insertPost(PostRequest postRequest);

    void updatePost(Long postId, PostRequest postRequest);

    void deletePostById(Long postId);
}