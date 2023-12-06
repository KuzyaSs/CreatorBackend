package ru.ermakov.creator.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.model.Follow;
import ru.ermakov.creator.model.FollowRequest;
import ru.ermakov.creator.service.FollowService;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class FollowController {
    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @GetMapping("users/{userId}/follows")
    List<Follow> getFollowsByUserId(@PathVariable String userId) {
        return followService.getFollowsByUserId(userId);
    }

    @GetMapping("follows")
    Follow getFollowByUserAndCreatorIds(@RequestParam String userId, @RequestParam String creatorId) {
        return followService.getFollowByUserAndCreatorIds(new FollowRequest(userId, creatorId));
    }

    @GetMapping("users/{userId}/followers/count")
    Long getFollowerCountByUserId(@PathVariable String userId) {
        return followService.getFollowerCountByUserId(userId);
    }

    @PostMapping("follows")
    void insertFollow(@RequestBody FollowRequest followRequest) {
        followService.insertFollow(followRequest);
    }

    @DeleteMapping("follows/{followId}")
    void deleteFollowById(@PathVariable Long followId) {
        followService.deleteFollowById(followId);
    }
}
