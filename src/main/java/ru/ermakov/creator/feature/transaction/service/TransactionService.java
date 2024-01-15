package ru.ermakov.creator.feature.transaction.service;

import ru.ermakov.creator.feature.transaction.model.CreditGoalTransaction;
import ru.ermakov.creator.feature.transaction.model.CreditGoalTransactionRequest;
import ru.ermakov.creator.feature.transaction.model.UserTransaction;
import ru.ermakov.creator.feature.transaction.model.UserTransactionRequest;

import java.util.List;

public interface TransactionService {
    List<UserTransaction> getUserTransactionPageByUserId(String userId, Long userTransactionId, Integer limit);

    List<CreditGoalTransaction> getCreditGoalTransactionPageByCreditGoalId(
            Long creditGoalId,
            Integer limit,
            Integer offset
    );

    Long getBalanceByUserId(String userId);

    Long getBalanceByCreditGoalId(Long creditGoalId);

    void insertUserTransaction(UserTransactionRequest userTransactionRequest);

    void insertCreditGoalTransaction(CreditGoalTransactionRequest creditGoalTransactionRequest);
}
