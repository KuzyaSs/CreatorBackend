package ru.ermakov.creator.feature.transaction.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.transaction.exception.InsufficientFundsException;
import ru.ermakov.creator.feature.transaction.exception.TransactionNotFoundException;
import ru.ermakov.creator.feature.transaction.model.CreditGoalTransaction;
import ru.ermakov.creator.feature.transaction.model.CreditGoalTransactionRequest;
import ru.ermakov.creator.feature.transaction.model.UserTransaction;
import ru.ermakov.creator.feature.transaction.model.UserTransactionRequest;
import ru.ermakov.creator.feature.transaction.repository.TransactionDao;
import ru.ermakov.creator.feature.user.service.UserService;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDao transactionDao;
    private final UserService userService;
    private final CreditGoalService creditGoalService;

    private static final Long CLOSE_CREDIT_GOAL_TRANSACTION_ID = 3L;

    public TransactionServiceImpl(TransactionDao transactionDao, UserService userService, CreditGoalService creditGoalService) {
        this.transactionDao = transactionDao;
        this.userService = userService;
        this.creditGoalService = creditGoalService;
    }

    @Override
    public List<UserTransaction> getUserTransactionsByPage(Long userTransactionId, Integer limit) {
        return transactionDao.getUserTransactionsByPage(userTransactionId, limit)
                .stream()
                .map(userTransactionEntity ->
                        new UserTransaction(
                                userTransactionEntity.id(),
                                userService.getUserById(userTransactionEntity.senderUserId()),
                                userService.getUserById(userTransactionEntity.receiverUserId()),
                                transactionDao.getTransactionTypeById(userTransactionEntity.transactionTypeId())
                                        .orElseThrow(TransactionNotFoundException::new),
                                userTransactionEntity.amount(),
                                userTransactionEntity.message(),
                                userTransactionEntity.transactionDate()
                        )
                ).toList();
    }

    @Override
    public List<CreditGoalTransaction> getCreditGoalTransactionsByPage(Long creditGoalTransactionId, Integer limit) {
        return transactionDao.getCreditGoalTransactionsByPage(creditGoalTransactionId, limit)
                .stream()
                .map(creditGoalTransactionEntity ->
                        new CreditGoalTransaction(
                                creditGoalTransactionEntity.id(),
                                creditGoalService.getCreditGoalById(creditGoalTransactionEntity.creditGoalId()),
                                userService.getUserById(creditGoalTransactionEntity.userId()),
                                transactionDao.getTransactionTypeById(creditGoalTransactionEntity.transactionTypeId())
                                        .orElseThrow(TransactionNotFoundException::new),
                                creditGoalTransactionEntity.amount(),
                                creditGoalTransactionEntity.message(),
                                creditGoalTransactionEntity.transactionDate()
                        )
                ).toList();
    }

    @Override
    public void insertUserTransaction(UserTransactionRequest userTransactionRequest) {
        if (transactionDao.getBalanceByUserId(userTransactionRequest.senderUserId()) < userTransactionRequest.amount()) {
            throw new InsufficientFundsException();
        }
        transactionDao.insertUserTransaction(userTransactionRequest);
    }

    @Override
    public void insertCreditGoalTransaction(CreditGoalTransactionRequest creditGoalTransactionRequest) {
        if (!creditGoalTransactionRequest.transactionTypeId().equals(CLOSE_CREDIT_GOAL_TRANSACTION_ID) &&
            transactionDao.getBalanceByUserId(creditGoalTransactionRequest.userId()) < creditGoalTransactionRequest.amount()
        ) {
            throw new InsufficientFundsException();
        }
        transactionDao.insertCreditGoalTransaction(creditGoalTransactionRequest);
    }
}
