package ru.ermakov.creator.feature.goal.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.feature.goal.model.CreditGoalEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

public class CreditGoalRowMapper implements RowMapper<CreditGoalEntity> {
    @Override
    public CreditGoalEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CreditGoalEntity(
                rs.getLong(ID_COLUMN),
                rs.getString(CREATOR_ID_COLUMN),
                rs.getLong(TARGET_BALANCE_COLUMN),
                rs.getString(DESCRIPTION_COLUMN),
                rs.getTimestamp(CREATION_DATE_COLUMN)
        );
    }
}
