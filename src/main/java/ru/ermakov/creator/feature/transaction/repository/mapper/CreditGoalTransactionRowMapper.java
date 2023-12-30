package ru.ermakov.creator.feature.transaction.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.feature.transaction.model.CreditGoalTransactionEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

public class CreditGoalTransactionRowMapper implements RowMapper<CreditGoalTransactionEntity> {
    @Override
    public CreditGoalTransactionEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CreditGoalTransactionEntity(
                rs.getLong(ID_COLUMN),
                rs.getLong(CREDIT_GOAL_ID_COLUMN),
                rs.getString(SENDER_USER_ID_COLUMN),
                rs.getLong(TRANSACTION_TYPE_ID_COLUMN),
                rs.getLong(AMOUNT_COLUMN),
                rs.getString(MESSAGE_COLUMN),
                rs.getTimestamp(TRANSACTION_DATE_COLUMN)
        );
    }
}
