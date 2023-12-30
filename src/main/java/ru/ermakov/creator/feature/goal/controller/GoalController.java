package ru.ermakov.creator.feature.goal.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.feature.goal.model.CreditGoal;
import ru.ermakov.creator.feature.goal.model.CreditGoalRequest;
import ru.ermakov.creator.feature.goal.service.GoalService;

import java.util.List;

@RestController
@RequestMapping("api")
public class GoalController {
    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("creators/{creatorId}/credit-goals")
    public List<CreditGoal> getCreditGoalsByCreatorId(@PathVariable("creatorId") String creatorId) {
        return goalService.getCreditGoalsByCreatorId(creatorId);
    }

    @GetMapping("credit-goals/{creditGoalId}")
    public CreditGoal getCreditGoalById(@PathVariable("creditGoalId") Long creditGoalId) {
        return goalService.getCreditGoalById(creditGoalId);
    }

    @PostMapping("credit-goals")
    public void insertCreditGoal(@RequestBody CreditGoalRequest creditGoalRequest) {
        goalService.insertCreditGoal(creditGoalRequest);
    }

    @PutMapping("credit-goals/{creditGoalId}")
    public void updateCreditGoal(@PathVariable("creditGoalId") Long creditGoalId, @RequestBody CreditGoalRequest creditGoalRequest) {
        goalService.updateCreditGoal(creditGoalId, creditGoalRequest);
    }

    @DeleteMapping("credit-goals/{creditGoalId}")
    public void deleteCreditGoalById(@PathVariable("creditGoalId") Long creditGoalId) {
        goalService.deleteCreditGoalById(creditGoalId);
    }
}
