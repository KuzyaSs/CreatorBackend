package ru.ermakov.creator.feature.post.service;

import ru.ermakov.creator.feature.post.model.filter.BlogFilter;
import ru.ermakov.creator.feature.post.model.filter.FeedFilter;
import ru.ermakov.creator.feature.post.model.post.Post;
import ru.ermakov.creator.feature.post.model.post.PostRequest;

import java.util.List;

public interface PostService {
    // In the feed screen.
    List<Post> getFilteredPostPage(FeedFilter feedFilter, Long postId, Integer limit);

    // In the following screen.
    List<Post> getFilteredPostPageByUserId(String userId, FeedFilter feedFilter, Long postId, Integer limit);

    // In the blog screen.
    List<Post> getFilteredPostPageByCreatorId(String creatorId, BlogFilter blogFilter, Long postId, Integer limit);

    // In the search screen.
    List<Post> getPostPageBySearchQuery(String searchQuery, Long postId, Integer limit);

    Post getPostById(Long postId);

    void insertPost(PostRequest postRequest);

    void updatePost(Long postId, PostRequest postRequest);

    void deletePostById(Long postId);
}