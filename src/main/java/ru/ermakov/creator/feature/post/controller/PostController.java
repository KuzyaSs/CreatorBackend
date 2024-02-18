package ru.ermakov.creator.feature.post.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.feature.postLike.model.LikeRequest;
import ru.ermakov.creator.feature.post.model.Post;
import ru.ermakov.creator.feature.post.model.PostRequest;
import ru.ermakov.creator.feature.post.service.PostService;

import java.util.List;

@RestController
@RequestMapping(path = "api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("discover")
    List<Post> getFilteredPostPageByUserId(
            @RequestParam String userId,
            @RequestParam String postType,
            @RequestParam(required = false, defaultValue = "") List<Long> categoryIds,
            @RequestParam Long postId,
            @RequestParam Integer limit
    ) {
        return postService.getFilteredPostPageByUserId(userId, postType, categoryIds, postId, limit);
    }

    @GetMapping("following")
    List<Post> getFilteredFollowingPostPageByUserId(
            @RequestParam String userId,
            @RequestParam String postType,
            @RequestParam(required = false, defaultValue = "") List<Long> categoryIds,
            @RequestParam Long postId,
            @RequestParam Integer limit
    ) {
        return postService.getFilteredFollowingPostPageByUserId(userId, postType, categoryIds, postId, limit);
    }

    @GetMapping("creators/{creatorId}")
    List<Post> getFilteredPostPageByUserAndCreatorIds(
            @RequestParam String userId,
            @PathVariable(name = "creatorId") String creatorId,
            @RequestParam String postType,
            @RequestParam(required = false, defaultValue = "") List<Long> tagIds,
            @RequestParam Long postId,
            @RequestParam Integer limit
    ) {
        return postService.getFilteredPostPageByUserAndCreatorIds(userId, creatorId, postType, tagIds, postId, limit);
    }

    @GetMapping("search")
    List<Post> getPostPageByUserIdAndSearchQuery(
            @RequestParam String userId,
            @RequestParam String searchQuery,
            @RequestParam Long postId,
            @RequestParam Integer limit
    ) {
        return postService.getPostPageByUserIdAndSearchQuery(userId, searchQuery, postId, limit);
    }

    @GetMapping("{postId}")
    Post getPostByUserAndPostIds(@RequestParam String userId, @PathVariable(name = "postId") Long postId) {
        return postService.getPostByUserAndPostIds(userId, postId);
    }

    @PostMapping
    void insertPost(@RequestBody PostRequest postRequest) {
        postService.insertPost(postRequest);
    }

    @PutMapping("{postId}")
    void updatePost(@PathVariable(name = "postId") Long postId, @RequestBody PostRequest postRequest) {
        postService.updatePost(postId, postRequest);
    }

    @DeleteMapping("{postId}")
    void deletePostById(@PathVariable(name = "postId") Long postId) {
        postService.deletePostById(postId);
    }

    @PostMapping("likes")
    void insertLikeToPost(@RequestBody LikeRequest likeRequest) {
        postService.insertLikeToPost(likeRequest);
    }

    @DeleteMapping("{postId}/likes")
    void deleteLikeFromPost(@PathVariable(name = "postId") Long postId, @RequestParam String userId) {
        postService.deleteLikeFromPost(postId, userId);
    }
}
