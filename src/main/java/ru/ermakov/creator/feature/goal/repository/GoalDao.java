package ru.ermakov.creator.feature.goal.repository;

import ru.ermakov.creator.feature.goal.model.CreditGoalEntity;
import ru.ermakov.creator.feature.goal.model.CreditGoalRequest;

import java.util.List;
import java.util.Optional;

public interface GoalDao {
    List<CreditGoalEntity> getCreditGoalsByCreatorId(String creatorId);

    Optional<CreditGoalEntity> getCreditGoalById(Long creditGoalId);

    void insertCreditGoal(CreditGoalRequest creditGoalRequest);

    void updateCreditGoal(Long creditGoalId, CreditGoalRequest creditGoalRequest);

    void deleteCreditGoalById(Long creditGoalId);
}
