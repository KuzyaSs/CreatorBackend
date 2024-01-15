package ru.ermakov.creator.feature.transaction.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.transaction.model.*;
import ru.ermakov.creator.feature.transaction.repository.mapper.CreditGoalTransactionRowMapper;
import ru.ermakov.creator.feature.transaction.repository.mapper.TransactionTypeRowMapper;
import ru.ermakov.creator.feature.transaction.repository.mapper.UserTransactionRowMapper;

import java.util.List;
import java.util.Optional;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

@Repository
public class TransactionDaoImpl implements TransactionDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TransactionDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserTransactionEntity> getUserTransactionPageByUserId(String userId, Long userTransactionId, Integer limit) {
        String query = """
                SELECT *
                FROM user_transaction
                WHERE id < :id
                    AND (sender_user_id = :user_id
                    OR receiver_user_id = :user_id)
                ORDER BY id DESC
                LIMIT :limit
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, userTransactionId)
                .addValue(USER_ID_COLUMN, userId)
                .addValue(LIMIT_PARAM, limit);

        return jdbcTemplate.query(query, sqlParameterSource, new UserTransactionRowMapper());
    }

    @Override
    public List<CreditGoalTransactionEntity> getCreditGoalTransactionPageByCreditGoalId(
            Long creditGoalId,
            Integer limit,
            Integer offset
    ) {
        String query = """
                SELECT *
                FROM credit_goal_transaction
                WHERE credit_goal_id = :credit_goal_id
                LIMIT :limit
                OFFSET :offset
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(CREDIT_GOAL_ID_COLUMN, creditGoalId)
                .addValue(LIMIT_PARAM, limit)
                .addValue(OFFSET_PARAM, offset);

        return jdbcTemplate.query(query, sqlParameterSource, new CreditGoalTransactionRowMapper());
    }

    @Override
    public Long getBalanceByUserId(String userId) {
        String query = """
                SELECT COALESCE(SUM(
                    CASE WHEN (name in ('Top-up', 'Credit goal closure') AND receiver_user_id = :user_id)
                        OR (name in ('Subscription purchase', 'Transfer to a user') AND sender_user_id != :user_id AND receiver_user_id = :user_id)
                    THEN amount
                         WHEN name = 'Withdrawal'
                            OR (name in ('Subscription purchase', 'Transfer to a user', 'Transfer to a credit goal') AND sender_user_id = :user_id AND receiver_user_id != :user_id)
                         THEN amount * (-1)
                         ELSE 0
                    END
                ), 0) AS balance
                FROM user_transaction
                JOIN transaction_type ON user_transaction.transaction_type_id = transaction_type.id
                WHERE sender_user_id = :user_id
                    OR receiver_user_id = :user_id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(USER_ID_COLUMN, userId);

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Long.class);
    }

    @Override
    public Long getBalanceByCreditGoalId(Long creditGoalId) {
        String query = """
                SELECT COALESCE(SUM(
                    CASE WHEN name = 'Transfer to a credit goal' THEN amount
                         WHEN name = 'Credit goal closure' THEN amount * (-1)
                         ELSE 0
                    END
                ), 0) AS balance
                FROM credit_goal_transaction
                JOIN transaction_type ON credit_goal_transaction.transaction_type_id = transaction_type.id
                WHERE credit_goal_id = :credit_goal_id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(CREDIT_GOAL_ID_COLUMN, creditGoalId);

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Long.class);
    }

    @Override
    public Optional<TransactionType> getTransactionTypeById(Long transactionTypeId) {
        String query = """
                SELECT *
                FROM transaction_type
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, transactionTypeId);

        return jdbcTemplate.query(query, sqlParameterSource, new TransactionTypeRowMapper())
                .stream()
                .findFirst();
    }

    @Override
    public void insertUserTransaction(UserTransactionRequest userTransactionRequest) {
        String query = """
                INSERT INTO user_transaction(sender_user_id, receiver_user_id, transaction_type_id, amount, message)
                VALUES (:sender_user_id, :receiver_user_id, :transaction_type_id, :amount, :message)
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(SENDER_USER_ID_COLUMN, userTransactionRequest.senderUserId())
                .addValue(RECEIVER_USER_ID_COLUMN, userTransactionRequest.receiverUserId())
                .addValue(TRANSACTION_TYPE_ID_COLUMN, userTransactionRequest.transactionTypeId())
                .addValue(AMOUNT_COLUMN, userTransactionRequest.amount())
                .addValue(MESSAGE_COLUMN, userTransactionRequest.message());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void insertCreditGoalTransaction(CreditGoalTransactionRequest creditGoalTransactionRequest) {
        String query = """
                INSERT INTO credit_goal_transaction(credit_goal_id, sender_user_id, transaction_type_id, amount, message)
                VALUES (:credit_goal_id, :sender_user_id, :transaction_type_id, :amount, :message)
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(CREDIT_GOAL_ID_COLUMN, creditGoalTransactionRequest.creditGoalId())
                .addValue(SENDER_USER_ID_COLUMN, creditGoalTransactionRequest.senderUserId())
                .addValue(TRANSACTION_TYPE_ID_COLUMN, creditGoalTransactionRequest.transactionTypeId())
                .addValue(AMOUNT_COLUMN, creditGoalTransactionRequest.amount())
                .addValue(MESSAGE_COLUMN, creditGoalTransactionRequest.message());

        jdbcTemplate.update(query, sqlParameterSource);
    }
}
