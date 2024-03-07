package ru.ermakov.creator.feature.postComment.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.feature.postComment.model.PostComment;
import ru.ermakov.creator.feature.postComment.model.PostCommentRequest;
import ru.ermakov.creator.feature.postComment.service.PostCommentService;

import java.util.List;

@RestController
@RequestMapping(path = "api/posts")
public class PostCommentController {
    private final PostCommentService postCommentService;

    public PostCommentController(PostCommentService postCommentService) {
        this.postCommentService = postCommentService;
    }

    @GetMapping("{postId}/comments")
    List<PostComment> getPostCommentPageByPostAndUserIds(
            @PathVariable(name = "postId") Long postId,
            @RequestParam("userId") String userId,
            @RequestParam(required = false, defaultValue = "-1") Long replyCommentId,
            @RequestParam Long postCommentId,
            @RequestParam Integer limit
    ) {
        return postCommentService.getPostCommentPageByPostAndUserIds(postId, userId, replyCommentId, postCommentId, limit);
    }

    @GetMapping("comments/{postCommentId}")
    PostComment getPostCommentByCommentAndUserIds(
            @PathVariable(name = "postCommentId") Long postCommentId,
            @RequestParam("userId") String userId
    ) {
        return postCommentService.getPostCommentByCommentAndUserIds(postCommentId, userId);
    }

    @PostMapping("comments")
    void insertPostComment(@RequestBody PostCommentRequest postCommentRequest) {
        postCommentService.insertPostComment(postCommentRequest);
    }

    @PutMapping("comments/{postCommentId}")
    void updatePostComment(
            @PathVariable(name = "postCommentId") Long postCommentId,
            @RequestBody PostCommentRequest postCommentRequest
    ) {
        postCommentService.updatePostComment(postCommentId, postCommentRequest);
    }

    @DeleteMapping("comments/{postCommentId}")
    void deletePostCommentById(@PathVariable(name = "postCommentId") Long postCommentId) {
        postCommentService.deletePostCommentById(postCommentId);
    }
}