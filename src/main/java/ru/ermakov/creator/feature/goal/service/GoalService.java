package ru.ermakov.creator.feature.goal.service;

import ru.ermakov.creator.feature.goal.model.CreditGoal;
import ru.ermakov.creator.feature.goal.model.CreditGoalRequest;

import java.util.List;

public interface GoalService {
    List<CreditGoal> getCreditGoalsByCreatorId(String creatorId);

    CreditGoal getCreditGoalById(Long creditGoalId);

    void insertCreditGoal(CreditGoalRequest creditGoalRequest);

    void updateCreditGoal(Long creditGoalId, CreditGoalRequest creditGoalRequest);

    void deleteCreditGoalById(Long creditGoalId);
}
