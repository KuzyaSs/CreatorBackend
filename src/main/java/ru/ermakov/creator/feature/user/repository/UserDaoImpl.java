package ru.ermakov.creator.feature.user.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.user.repository.mapper.UserRowMapper;
import ru.ermakov.creator.feature.user.model.AuthUser;
import ru.ermakov.creator.feature.user.model.User;

import java.util.List;
import java.util.Optional;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

@Repository
public class UserDaoImpl implements UserDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getUserPageBySearchQuery(String searchQuery, Integer limit, Integer offset) {
        String query = """
                SELECT *
                FROM public.user
                WHERE LOWER(username) LIKE LOWER(:search_query)
                ORDER BY username
                LIMIT :limit
                OFFSET :offset
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(SEARCH_QUERY_PARAM, "%" + searchQuery + "%")
                .addValue(LIMIT_PARAM, limit)
                .addValue(OFFSET_PARAM, offset);


        return jdbcTemplate.query(query, sqlParameterSource, new UserRowMapper());
    }

    @Override
    public Optional<User> getUserById(String userId) {
        String query = """
                SELECT *
                FROM public.user
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, userId);

        return jdbcTemplate.query(query, sqlParameterSource, new UserRowMapper())
                .stream()
                .findFirst();
    }

    @Override
    public Boolean userExistsById(String userId) {
        String query = """
                SELECT EXISTS(
                    SELECT 1
                    FROM public.user
                    WHERE id = :id
                )
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, userId);

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Boolean.class);
    }

    @Override
    public Boolean userExistsByUsername(String username, String userId) {
        String query = """
                SELECT EXISTS(
                    SELECT 1
                    FROM public.user
                    WHERE username = :username
                        AND id != :id
                )
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USERNAME_COLUMN, username)
                .addValue(ID_COLUMN, userId);

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Boolean.class);
    }

    @Override
    public void insertUser(AuthUser authUser) {
        String query = """
                INSERT INTO public.user (id, username, email)
                VALUES (:id, :username, :email)
                     """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, authUser.id())
                .addValue(USERNAME_COLUMN, authUser.username())
                .addValue(EMAIL_COLUMN, authUser.email());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void updateUser(User user) {
        String query = """
                UPDATE public.user
                SET username = :username,
                    bio = :bio,
                    profile_avatar_url = :profile_avatar_url,
                    profile_background_url = :profile_background_url
                WHERE id = :id
                     """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, user.id())
                .addValue(USERNAME_COLUMN, user.username())
                .addValue(BIO_COLUMN, user.bio())
                .addValue(PROFILE_AVATAR_URL_COLUMN, user.profileAvatarUrl())
                .addValue(PROFILE_BACKGROUND_URL_COLUMN, user.profileBackgroundUrl());

        jdbcTemplate.update(query, sqlParameterSource);
    }
}