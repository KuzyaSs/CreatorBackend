package ru.ermakov.creator.feature.creditGoal.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.creditGoal.model.CreditGoalEntity;
import ru.ermakov.creator.feature.creditGoal.model.CreditGoalRequest;
import ru.ermakov.creator.feature.creditGoal.repository.mapper.CreditGoalRowMapper;

import java.util.List;
import java.util.Optional;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

@Repository
public class CreditGoalDaoImpl implements CreditGoalDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public CreditGoalDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CreditGoalEntity> getCreditGoalsByCreatorId(String creatorId) {
        String query = """
                SELECT *
                FROM credit_goal
                WHERE creator_id = :creator_id
                    AND is_closed = FALSE
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(CREATOR_ID_COLUMN, creatorId);

        return jdbcTemplate.query(query, sqlParameterSource, new CreditGoalRowMapper());
    }

    @Override
    public Optional<CreditGoalEntity> getCreditGoalById(Long creditGoalId) {
        String query = """
                SELECT *
                FROM credit_goal
                WHERE id = :id
                    AND is_closed = FALSE
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, creditGoalId);

        return jdbcTemplate.query(query, sqlParameterSource, new CreditGoalRowMapper())
                .stream()
                .findFirst();
    }

    @Override
    public void insertCreditGoal(CreditGoalRequest creditGoalRequest) {
        String query = """
                INSERT INTO credit_goal(creator_id, target_balance, description)
                VALUES (:creator_id, :target_balance, :description)
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(CREATOR_ID_COLUMN, creditGoalRequest.creatorId())
                .addValue(TARGET_BALANCE_COLUMN, creditGoalRequest.targetBalance())
                .addValue(DESCRIPTION_COLUMN, creditGoalRequest.description());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void updateCreditGoal(Long creditGoalId, CreditGoalRequest creditGoalRequest) {
        String query = """
                UPDATE credit_goal
                SET target_balance = :target_balance,
                    description = :description
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, creditGoalId)
                .addValue(TARGET_BALANCE_COLUMN, creditGoalRequest.targetBalance())
                .addValue(DESCRIPTION_COLUMN, creditGoalRequest.description());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void deleteCreditGoalById(Long creditGoalId) {
        String query = """
                UPDATE credit_goal
                SET is_closed = TRUE
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, creditGoalId);

        jdbcTemplate.update(query, sqlParameterSource);
    }
}
