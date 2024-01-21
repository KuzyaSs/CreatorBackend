package ru.ermakov.creator.feature.creditGoal.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.creator.service.CreatorService;
import ru.ermakov.creator.feature.creditGoal.exception.CreditGoalNotFoundException;
import ru.ermakov.creator.feature.creditGoal.model.CreditGoal;
import ru.ermakov.creator.feature.creditGoal.model.CreditGoalEntity;
import ru.ermakov.creator.feature.creditGoal.model.CreditGoalRequest;
import ru.ermakov.creator.feature.creditGoal.repository.CreditGoalDao;
import ru.ermakov.creator.feature.transaction.repository.TransactionDao;

import java.util.List;

@Service
public class CreditGoalServiceImpl implements CreditGoalService {
    private final CreditGoalDao creditGoalDao;
    private final CreatorService creatorService;
    private final TransactionDao transactionDao;

    public CreditGoalServiceImpl(CreditGoalDao creditGoalDao, CreatorService creatorService, TransactionDao transactionDao) {
        this.creditGoalDao = creditGoalDao;
        this.creatorService = creatorService;
        this.transactionDao = transactionDao;
    }

    @Override
    public List<CreditGoal> getCreditGoalsByCreatorId(String creatorId) {
        return creditGoalDao.getCreditGoalsByCreatorId(creatorId)
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
        CreditGoalEntity creditGoalEntity = creditGoalDao.getCreditGoalById(creditGoalId)
                .orElseThrow(CreditGoalNotFoundException::new);
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
        creditGoalDao.insertCreditGoal(creditGoalRequest);
    }

    @Override
    public void updateCreditGoal(Long creditGoalId, CreditGoalRequest creditGoalRequest) {
        creditGoalDao.updateCreditGoal(creditGoalId, creditGoalRequest);
    }

    @Override
    public void deleteCreditGoalById(Long creditGoalId) {
        creditGoalDao.deleteCreditGoalById(creditGoalId);
    }
}
