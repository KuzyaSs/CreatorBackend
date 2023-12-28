package ru.ermakov.creator.feature.subscription.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.feature.subscription.model.Subscription;
import ru.ermakov.creator.feature.subscription.model.SubscriptionRequest;
import ru.ermakov.creator.feature.subscription.service.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("creators/{creatorId}/subscriptions")
    List<Subscription> getSubscriptionsByCreatorId(@PathVariable String creatorId) {
        return subscriptionService.getSubscriptionsByCreatorId(creatorId);
    }

    @GetMapping("subscriptions/{subscriptionId}")
    Subscription getSubscriptionById(@PathVariable Long subscriptionId) {
        return subscriptionService.getSubscriptionById(subscriptionId);
    }

    @PostMapping("subscriptions")
    void insertSubscription(@RequestBody SubscriptionRequest subscriptionRequest) {
        subscriptionService.insertSubscription(subscriptionRequest);
    }

    @PutMapping("subscriptions/{subscriptionId}")
    void updateSubscription(@PathVariable Long subscriptionId, @RequestBody SubscriptionRequest subscriptionRequest) {
        subscriptionService.updateSubscription(subscriptionId, subscriptionRequest);
    }

    @DeleteMapping("subscriptions/{subscriptionId}")
    void deleteSubscriptionById(@PathVariable Long subscriptionId) {
        subscriptionService.deleteSubscriptionById(subscriptionId);
    }
}
