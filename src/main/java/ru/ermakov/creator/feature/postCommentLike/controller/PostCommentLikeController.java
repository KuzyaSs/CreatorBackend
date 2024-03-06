package ru.ermakov.creator.feature.postCommentLike.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.feature.postCommentLike.model.PostCommentLikeRequest;
import ru.ermakov.creator.feature.postCommentLike.service.PostCommentLikeService;

@RestController
@RequestMapping("api/posts/comments")
public class PostCommentLikeController {
    private final PostCommentLikeService postCommentLikeService;

    public PostCommentLikeController(PostCommentLikeService postCommentLikeService) {
        this.postCommentLikeService = postCommentLikeService;
    }

    @PostMapping("likes")
    public void insertPostCommentLike(@RequestBody PostCommentLikeRequest postCommentLikeRequest) {
        postCommentLikeService.insertPostCommentLike(postCommentLikeRequest);
    }

    @PutMapping("{postCommentId}/likes")
    public void deletePostCommentLike(
            @PathVariable("postCommentId") Long postCommentId,
            @RequestParam String userId
    ) {
        PostCommentLikeRequest postCommentLikeRequest = new PostCommentLikeRequest(userId, postCommentId);
        postCommentLikeService.deletePostCommentLike(postCommentLikeRequest);
    }
}