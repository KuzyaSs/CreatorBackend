package ru.ermakov.creator.feature.transaction.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.goal.service.GoalService;
import ru.ermakov.creator.feature.transaction.exception.InsufficientFundsInAccountException;
import ru.ermakov.creator.feature.transaction.exception.InsufficientFundsInGoalException;
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
    private final GoalService goalService;

    private static final Long WITHDRAWAL_TRANSACTION_ID = 2L;
    private static final Long CREDIT_GOAL_CLOSURE_TRANSACTION_ID = 3L;
    private static final Long SUBSCRIPTION_PURCHASE_TRANSACTION_ID = 4L;
    private static final Long TRANSFER_TO_USER_TRANSACTION_ID = 5L;
    private static final Long TRANSFER_TO_CREDIT_GOAL_TRANSACTION_ID = 6L;

    public TransactionServiceImpl(TransactionDao transactionDao, UserService userService, GoalService goalService) {
        this.transactionDao = transactionDao;
        this.userService = userService;
        this.goalService = goalService;
    }

    @Override
    public List<UserTransaction> getUserTransactionPageByUserId(String userId, Long userTransactionId, Integer limit) {
        return transactionDao.getUserTransactionPageByUserId(userId, userTransactionId, limit)
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
    public List<CreditGoalTransaction> getCreditGoalTransactionPageByCreditGoalId(
            Long creditGoalId,
            Integer limit,
            Integer offset
    ) {
        return transactionDao.getCreditGoalTransactionPageByCreditGoalId(creditGoalId, limit, offset)
                .stream()
                .map(creditGoalTransactionEntity ->
                        new CreditGoalTransaction(
                                creditGoalTransactionEntity.id(),
                                goalService.getCreditGoalById(creditGoalTransactionEntity.creditGoalId()),
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
    public Long getBalanceByUserId(String userId) {
        return transactionDao.getBalanceByUserId(userId);
    }

    @Override
    public Long getBalanceByCreditGoalId(Long creditGoalId) {
        return transactionDao.getBalanceByCreditGoalId(creditGoalId);
    }

    @Override
    public void insertUserTransaction(UserTransactionRequest userTransactionRequest) {
        if ((userTransactionRequest.transactionTypeId().equals(WITHDRAWAL_TRANSACTION_ID) ||
             userTransactionRequest.transactionTypeId().equals(SUBSCRIPTION_PURCHASE_TRANSACTION_ID) ||
             userTransactionRequest.transactionTypeId().equals(TRANSFER_TO_USER_TRANSACTION_ID)) &&
            getBalanceByUserId(userTransactionRequest.senderUserId()) < userTransactionRequest.amount()
        ) {
            throw new InsufficientFundsInAccountException();
        }

        transactionDao.insertUserTransaction(userTransactionRequest);
    }

    @Override
    public void insertCreditGoalTransaction(CreditGoalTransactionRequest creditGoalTransactionRequest) {
        if (creditGoalTransactionRequest.transactionTypeId().equals(TRANSFER_TO_CREDIT_GOAL_TRANSACTION_ID) &&
            getBalanceByUserId(creditGoalTransactionRequest.senderUserId()) < creditGoalTransactionRequest.amount()
        ) {
            throw new InsufficientFundsInAccountException();
        }

        if (creditGoalTransactionRequest.transactionTypeId().equals(CREDIT_GOAL_CLOSURE_TRANSACTION_ID) &&
            getBalanceByCreditGoalId(creditGoalTransactionRequest.creditGoalId()) < creditGoalTransactionRequest.amount()
        ) {
            throw new InsufficientFundsInGoalException();
        }

        UserTransactionRequest userTransactionRequest = new UserTransactionRequest(
                creditGoalTransactionRequest.senderUserId(),
                creditGoalTransactionRequest.receiverUserId(),
                creditGoalTransactionRequest.transactionTypeId(),
                creditGoalTransactionRequest.amount(),
                creditGoalTransactionRequest.message()
        );
        insertUserTransaction(userTransactionRequest);

        transactionDao.insertCreditGoalTransaction(creditGoalTransactionRequest);
    }
}
