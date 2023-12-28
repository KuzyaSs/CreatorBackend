package ru.ermakov.creator.feature.transaction.repository;

import ru.ermakov.creator.feature.transaction.model.*;

import java.util.List;
import java.util.Optional;

public class TransactionDaoImpl implements TransactionDao {
    @Override
    public List<UserTransactionEntity> getUserTransactionsByPage(Long userTransactionId, Integer limit) {
        return null;
    }

    @Override
    public List<CreditGoalTransactionEntity> getCreditGoalTransactionsByPage(Long creditGoalTransactionId, Integer limit) {
        return null;
    }

    @Override
    public Long getBalanceByUserId(String userId) {
        return null;
    }

    @Override
    public Long getBalanceByCreditGoalId(Long creditGoalId) {
        return null;
    }

    @Override
    public Optional<TransactionType> getTransactionTypeById(Long transactionTypeById) {
        return null;
    }

    @Override
    public void insertUserTransaction(UserTransactionRequest userTransactionRequest) {

    }

    @Override
    public void insertCreditGoalTransaction(CreditGoalTransactionRequest creditGoalTransactionRequest) {

    }
}
