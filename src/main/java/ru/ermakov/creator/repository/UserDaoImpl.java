package ru.ermakov.creator.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.model.SignUpData;
import ru.ermakov.creator.model.User;
import ru.ermakov.creator.repository.mapper.UserRowMapper;

import java.util.List;
import java.util.Optional;

import static ru.ermakov.creator.repository.Constants.*;

@Repository
public class UserDaoImpl implements UserDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUsersByPage(Integer currentId, Integer limit) {
        String sql = String.format("""
                SELECT *
                FROM users
                WHERE id > :id
                ORDER BY id
                LIMIT %s
                    """, limit);

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, currentId);

        return jdbcTemplate.query(sql, sqlParameterSource, new UserRowMapper());
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        String sql = """
                SELECT *
                FROM users
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, userId);

        return jdbcTemplate.query(sql, sqlParameterSource, new UserRowMapper())
                .stream()
                .findFirst();
    }

    @Override
    public Integer insertUser(SignUpData signUpData) {
        String sql = """
                INSERT INTO users(username, email)
                VALUES(:username, :email) RETURNING ID
                     """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USERNAME_COLUMN, signUpData.username())
                .addValue(EMAIL_COLUMN, signUpData.email());

        return jdbcTemplate.queryForObject(sql, sqlParameterSource, Integer.class);
    }
}