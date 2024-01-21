package ru.ermakov.creator.feature.creditGoal.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.feature.creditGoal.model.CreditGoal;
import ru.ermakov.creator.feature.creditGoal.model.CreditGoalRequest;
import ru.ermakov.creator.feature.creditGoal.service.CreditGoalService;

import java.util.List;

@RestController
@RequestMapping("api")
public class CreditGoalController {
    private final CreditGoalService creditGoalService;

    public CreditGoalController(CreditGoalService creditGoalService) {
        this.creditGoalService = creditGoalService;
    }

    @GetMapping("creators/{creatorId}/credit-goals")
    public List<CreditGoal> getCreditGoalsByCreatorId(@PathVariable("creatorId") String creatorId) {
        return creditGoalService.getCreditGoalsByCreatorId(creatorId);
    }

    @GetMapping("credit-goals/{creditGoalId}")
    public CreditGoal getCreditGoalById(@PathVariable("creditGoalId") Long creditGoalId) {
        return creditGoalService.getCreditGoalById(creditGoalId);
    }

    @PostMapping("credit-goals")
    public void insertCreditGoal(@RequestBody CreditGoalRequest creditGoalRequest) {
        creditGoalService.insertCreditGoal(creditGoalRequest);
    }

    @PutMapping("credit-goals/{creditGoalId}")
    public void updateCreditGoal(@PathVariable("creditGoalId") Long creditGoalId, @RequestBody CreditGoalRequest creditGoalRequest) {
        creditGoalService.updateCreditGoal(creditGoalId, creditGoalRequest);
    }

    @DeleteMapping("credit-goals/{creditGoalId}")
    public void deleteCreditGoalById(@PathVariable("creditGoalId") Long creditGoalId) {
        creditGoalService.deleteCreditGoalById(creditGoalId);
    }
}
