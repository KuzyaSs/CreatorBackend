package ru.ermakov.creator.feature.transaction.repository;

import ru.ermakov.creator.feature.transaction.model.*;

import java.util.List;
import java.util.Optional;

public interface TransactionDao {
    List<UserTransactionEntity> getUserTransactionPageByUserId(String userId, Integer limit, Integer offset);

    List<CreditGoalTransactionEntity> getCreditGoalTransactionPageByCreditGoalId(
            Long creditGoalId,
            Integer limit,
            Integer offset
    );

    Long getBalanceByUserId(String userId);

    Long getBalanceByCreditGoalId(Long creditGoalId);

    Optional<TransactionType> getTransactionTypeById(Long transactionTypeId);

    void insertUserTransaction(UserTransactionRequest userTransactionRequest);

    void insertCreditGoalTransaction(CreditGoalTransactionRequest creditGoalTransactionRequest);
}
