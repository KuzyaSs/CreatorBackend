package ru.ermakov.creator.feature.postLike.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.feature.postLike.model.LikeRequest;
import ru.ermakov.creator.feature.postLike.service.PostLikeService;

@RestController
@RequestMapping(path = "api/posts")
public class PostLikeController {
    private final PostLikeService postLikeService;

    public PostLikeController(PostLikeService postLikeService) {
        this.postLikeService = postLikeService;
    }

    @PostMapping("likes")
    void insertLikeToPost(@RequestBody LikeRequest likeRequest) {
        postLikeService.insertLike(likeRequest);
    }

    @DeleteMapping("{postId}/likes")
    void deleteLikeFromPost(@PathVariable(name = "postId") Long postId, @RequestParam String userId) {
        LikeRequest likeRequest = new LikeRequest(userId, postId);
        postLikeService.deleteLike(likeRequest);
    }
}