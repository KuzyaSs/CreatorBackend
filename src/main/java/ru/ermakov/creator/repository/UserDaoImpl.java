package ru.ermakov.creator.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.model.AuthUser;
import ru.ermakov.creator.model.User;
import ru.ermakov.creator.repository.mapper.UserRowMapper;

import java.util.List;
import java.util.Optional;

import static ru.ermakov.creator.repository.ColumnNameConstants.*;

@Repository
public class UserDaoImpl implements UserDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // fix
    @Override
    public List<User> getUsersByPage(Integer limit, Integer offset) {
        String sql = String.format("""
                SELECT *
                FROM public.user
                ORDER BY registration_date
                LIMIT %s
                OFFSET %s
                    """, limit, offset);

        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public Optional<User> getUserById(String userId) {
        String sql = """
                SELECT *
                FROM public.user
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, userId);

        return jdbcTemplate.query(sql, sqlParameterSource, new UserRowMapper())
                .stream()
                .findFirst();
    }

    @Override
    public Boolean userExistsById(String userId) {
        String sql = """
                SELECT EXISTS(
                    SELECT 1
                    FROM public.user
                    WHERE id = :id
                )
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, userId);

        return jdbcTemplate.queryForObject(sql, sqlParameterSource, Boolean.class);
    }

    @Override
    public Boolean checkUsernameUniqueness(String username, String currentUserId) {
        String sql = """
                SELECT NOT EXISTS(
                    SELECT 1
                    FROM public.user
                    WHERE username = :username AND id != :id
                )
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USERNAME_COLUMN, username)
                .addValue(ID_COLUMN, currentUserId);

        return jdbcTemplate.queryForObject(sql, sqlParameterSource, Boolean.class);
    }

    @Override
    public void insertUser(AuthUser authUser) {
        String sql = """
                INSERT INTO public.user(id, username, email)
                VALUES(:id, :username, :email)
                     """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, authUser.id())
                .addValue(USERNAME_COLUMN, authUser.username())
                .addValue(EMAIL_COLUMN, authUser.email());

        jdbcTemplate.update(sql, sqlParameterSource);
    }

    @Override
    public void updateUser(User user) {
        String sql = """
                UPDATE public.user
                SET username = :username,
                    bio = :bio,
                    profile_avatar_url = :profile_avatar_url,
                    profile_background_url = :profile_background_url
                WHERE id = :id
                     """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, user.getId())
                .addValue(USERNAME_COLUMN, user.getUsername())
                .addValue(BIO_COLUMN, user.getBio())
                .addValue(PROFILE_AVATAR_URL_COLUMN, user.getProfileAvatarUrl())
                .addValue(PROFILE_BACKGROUND_URL_COLUMN, user.getProfileBackgroundUrl());

        jdbcTemplate.update(sql, sqlParameterSource);
    }
}