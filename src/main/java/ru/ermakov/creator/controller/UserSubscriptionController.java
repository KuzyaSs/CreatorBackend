package ru.ermakov.creator.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.model.UserSubscription;
import ru.ermakov.creator.model.UserSubscriptionRequest;
import ru.ermakov.creator.service.UserSubscriptionService;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class UserSubscriptionController {
    private final UserSubscriptionService userSubscriptionService;

    public UserSubscriptionController(UserSubscriptionService userSubscriptionService) {
        this.userSubscriptionService = userSubscriptionService;
    }

    @GetMapping("users/{userId}/subscriptions")
    List<UserSubscription> getUserSubscriptionsByUserAndCreatorIds(@PathVariable String userId, @RequestParam String creatorId) {
        return userSubscriptionService.getUserSubscriptionsByUserAndCreatorIds(userId, creatorId);
    }

    @GetMapping("subscriptions/{subscriptionId}/count")
    Long getSubscriberCountBySubscriptionId(@PathVariable Long subscriptionId) {
        return userSubscriptionService.getSubscriberCountBySubscriptionId(subscriptionId);
    }

    @PostMapping("users/{userId}/subscriptions")
    void insertUserSubscription(@RequestBody UserSubscriptionRequest userSubscriptionRequest) {
        userSubscriptionService.insertUserSubscription(userSubscriptionRequest);
    }

    @DeleteMapping("users/{userId}/subscriptions/{userSubscriptionId}")
    void deleteUserSubscriptionById(@PathVariable Long userSubscriptionId) {
        userSubscriptionService.deleteUserSubscriptionById(userSubscriptionId);
    }
}
