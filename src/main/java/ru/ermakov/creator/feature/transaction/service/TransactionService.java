package ru.ermakov.creator.feature.transaction.service;

import ru.ermakov.creator.feature.transaction.model.CreditGoalTransaction;
import ru.ermakov.creator.feature.transaction.model.CreditGoalTransactionRequest;
import ru.ermakov.creator.feature.transaction.model.UserTransaction;
import ru.ermakov.creator.feature.transaction.model.UserTransactionRequest;

import java.util.List;

public interface TransactionService {
    List<UserTransaction> getUserTransactionsByPage(Long userTransactionId, Integer limit);

    List<CreditGoalTransaction> getCreditGoalTransactionsByPage(Long creditGoalTransactionId, Integer limit);

    void insertUserTransaction(UserTransactionRequest userTransactionRequest);

    void insertCreditGoalTransaction(CreditGoalTransactionRequest creditGoalTransactionRequest);
}
