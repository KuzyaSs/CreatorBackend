package ru.ermakov.creator.feature.creditGoal.service;

import ru.ermakov.creator.feature.creditGoal.model.CreditGoal;
import ru.ermakov.creator.feature.creditGoal.model.CreditGoalRequest;

import java.util.List;

public interface CreditGoalService {
    List<CreditGoal> getCreditGoalsByCreatorId(String creatorId);

    CreditGoal getCreditGoalById(Long creditGoalId);

    void insertCreditGoal(CreditGoalRequest creditGoalRequest);

    void updateCreditGoal(Long creditGoalId, CreditGoalRequest creditGoalRequest);

    void deleteCreditGoalById(Long creditGoalId);
}
