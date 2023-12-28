package ru.ermakov.creator.feature.transaction.repository;

import ru.ermakov.creator.feature.transaction.model.*;

import java.util.List;
import java.util.Optional;

public interface TransactionDao {
    List<UserTransactionEntity> getUserTransactionsByPage(Long userTransactionId, Integer limit);

    List<CreditGoalTransactionEntity> getCreditGoalTransactionsByPage(Long creditGoalTransactionId, Integer limit);

    Long getBalanceByUserId(String userId);

    Long getBalanceByCreditGoalId(Long creditGoalId);

    Optional<TransactionType> getTransactionTypeById(Long transactionTypeById);

    void insertUserTransaction(UserTransactionRequest userTransactionRequest);

    void insertCreditGoalTransaction(CreditGoalTransactionRequest creditGoalTransactionRequest);
}
