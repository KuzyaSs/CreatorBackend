package ru.ermakov.creator.feature.goal.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.creator.service.CreatorService;
import ru.ermakov.creator.feature.goal.exception.GoalNotFoundException;
import ru.ermakov.creator.feature.goal.model.CreditGoal;
import ru.ermakov.creator.feature.goal.model.CreditGoalEntity;
import ru.ermakov.creator.feature.goal.model.CreditGoalRequest;
import ru.ermakov.creator.feature.goal.repository.GoalDao;
import ru.ermakov.creator.feature.transaction.repository.TransactionDao;
import ru.ermakov.creator.feature.transaction.service.TransactionService;

import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {
    private final GoalDao goalDao;
    private final CreatorService creatorService;
    private final TransactionDao transactionDao;

    public GoalServiceImpl(GoalDao goalDao, CreatorService creatorService, TransactionDao transactionDao) {
        this.goalDao = goalDao;
        this.creatorService = creatorService;
        this.transactionDao = transactionDao;
    }

    @Override
    public List<CreditGoal> getCreditGoalsByCreatorId(String creatorId) {
        return goalDao.getCreditGoalsByCreatorId(creatorId)
                .stream()
                .map(creditGoalEntity ->
                        new CreditGoal(
                                creditGoalEntity.id(),
                                creatorService.getCreatorByUserId(creditGoalEntity.creatorId()),
                                creditGoalEntity.targetBalance(),
                                transactionDao.getBalanceByCreditGoalId(creditGoalEntity.id()),
                                creditGoalEntity.description(),
                                creditGoalEntity.creationDate()
                        )
                ).toList();
    }

    @Override
    public CreditGoal getCreditGoalById(Long creditGoalId) {
        CreditGoalEntity creditGoalEntity = goalDao.getCreditGoalById(creditGoalId)
                .orElseThrow(GoalNotFoundException::new);
        return new CreditGoal(
                creditGoalEntity.id(),
                creatorService.getCreatorByUserId(creditGoalEntity.creatorId()),
                creditGoalEntity.targetBalance(),
                transactionDao.getBalanceByCreditGoalId(creditGoalEntity.id()),
                creditGoalEntity.description(),
                creditGoalEntity.creationDate()
        );
    }

    @Override
    public void insertCreditGoal(CreditGoalRequest creditGoalRequest) {
        goalDao.insertCreditGoal(creditGoalRequest);
    }

    @Override
    public void updateCreditGoal(Long creditGoalId, CreditGoalRequest creditGoalRequest) {
        goalDao.updateCreditGoal(creditGoalId, creditGoalRequest);
    }

    @Override
    public void deleteCreditGoalById(Long creditGoalId) {
        goalDao.deleteCreditGoalById(creditGoalId);
    }
}
