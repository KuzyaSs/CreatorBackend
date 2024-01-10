package ru.ermakov.creator.feature.follow.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.feature.follow.model.Follow;
import ru.ermakov.creator.feature.follow.model.FollowRequest;
import ru.ermakov.creator.feature.follow.service.FollowService;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class FollowController {
    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @GetMapping("users/{userId}/follows")
    List<Follow> getFollowPageBySearchQueryAndUserId(
            @RequestParam String searchQuery,
            @RequestParam Integer limit,
            @RequestParam Integer offset,
            @PathVariable String userId
    ) {
        return followService.getFollowPageBySearchQueryAndUserId(searchQuery, limit, offset, userId);
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
