package ru.ermakov.creator.feature.postComment.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.feature.postComment.model.Comment;
import ru.ermakov.creator.feature.postComment.model.CommentRequest;
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
    List<Comment> getCommentPageByPostId(
            @PathVariable(name = "postId") Long postId,
            @RequestParam(required = false, defaultValue = "-1") Long replyCommentId,
            @RequestParam Long commentId,
            @RequestParam Integer limit
    ) {
        return postCommentService.getCommentPageByPostId(postId, replyCommentId, commentId, limit);
    }

    @GetMapping("comments/{commentId}")
    Comment getCommentById(@PathVariable(name = "commentId") Long commentId) {
        return postCommentService.getCommentById(commentId);
    }

    @PostMapping("comments")
    void insertComment(@RequestBody CommentRequest commentRequest) {
        postCommentService.insertComment(commentRequest);
    }

    @PutMapping("comments/{commentId}")
    void updateComment(@PathVariable(name = "commentId") Long commentId, @RequestBody CommentRequest commentRequest) {
        postCommentService.updateComment(commentId, commentRequest);
    }

    @DeleteMapping("comments/{commentId}")
    void deleteCommentById(@PathVariable(name = "commentId") Long commentId) {
        postCommentService.deleteCommentById(commentId);
    }
}