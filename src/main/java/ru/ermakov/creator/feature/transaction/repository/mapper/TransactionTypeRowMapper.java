package ru.ermakov.creator.feature.transaction.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.feature.transaction.model.CreditGoalTransactionEntity;
import ru.ermakov.creator.feature.transaction.model.TransactionType;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

public class TransactionTypeRowMapper implements RowMapper<TransactionType> {
    @Override
    public TransactionType mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TransactionType(
                rs.getLong(ID_COLUMN),
                rs.getString(NAME_COLUMN)
        );
    }
}
