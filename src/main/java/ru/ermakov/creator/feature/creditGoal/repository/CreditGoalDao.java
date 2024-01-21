package ru.ermakov.creator.feature.creditGoal.repository;

import ru.ermakov.creator.feature.creditGoal.model.CreditGoalEntity;
import ru.ermakov.creator.feature.creditGoal.model.CreditGoalRequest;

import java.util.List;
import java.util.Optional;

public interface CreditGoalDao {
    List<CreditGoalEntity> getCreditGoalsByCreatorId(String creatorId);

    Optional<CreditGoalEntity> getCreditGoalById(Long creditGoalId);

    void insertCreditGoal(CreditGoalRequest creditGoalRequest);

    void updateCreditGoal(Long creditGoalId, CreditGoalRequest creditGoalRequest);

    void deleteCreditGoalById(Long creditGoalId);
}
