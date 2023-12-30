package ru.ermakov.creator.feature.transaction.controller;

import org.springframework.web.bind.annotation.*;
import ru.ermakov.creator.feature.transaction.model.CreditGoalTransaction;
import ru.ermakov.creator.feature.transaction.model.CreditGoalTransactionRequest;
import ru.ermakov.creator.feature.transaction.model.UserTransaction;
import ru.ermakov.creator.feature.transaction.model.UserTransactionRequest;
import ru.ermakov.creator.feature.transaction.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping(path = "api")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("user-transactions")
    public List<UserTransaction> getUserTransactionsByPage(
            @RequestParam String userId,
            @RequestParam Integer limit,
            @RequestParam Integer offset
    ) {
        return transactionService.getUserTransactionsByPage(userId, limit, offset);
    }

    @GetMapping("credit-goal-transactions")
    public List<CreditGoalTransaction> getCreditGoalTransactionsByPage(
            @RequestParam Long creditGoalId,
            @RequestParam Integer limit,
            @RequestParam Integer offset
    ) {
        return transactionService.getCreditGoalTransactionsByCreditGoalIdByPage(creditGoalId, limit, offset);
    }

    @GetMapping("users/{userId}/balance")
    public Long getBalanceByUserId(@PathVariable("userId") String userId) {
        return transactionService.getBalanceByUserId(userId);
    }

    @GetMapping("credit-goals/{creditGoalId}/balance")
    public Long getBalanceByCreditGoalId(@PathVariable("creditGoalId") Long creditGoalId) {
        return transactionService.getBalanceByCreditGoalId(creditGoalId);
    }

    @PostMapping("user-transactions")
    public void insertUserTransaction(@RequestBody UserTransactionRequest userTransactionRequest) {
        transactionService.insertUserTransaction(userTransactionRequest);
    }

    @PostMapping("credit-goal-transactions")
    public void insertCreditGoalTransaction(@RequestBody CreditGoalTransactionRequest creditGoalTransactionRequest) {
        transactionService.insertCreditGoalTransaction(creditGoalTransactionRequest);
    }
}