package ru.ermakov.creator.feature.follow.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.follow.model.FollowEntity;
import ru.ermakov.creator.feature.follow.model.FollowRequest;
import ru.ermakov.creator.feature.follow.repository.mapper.FollowRowMapper;

import java.util.List;
import java.util.Optional;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

@Repository
public class FollowDaoImpl implements FollowDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FollowDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<FollowEntity> getFollowPageBySearchQueryAndUserId(
            String searchQuery,
            Integer limit,
            Integer offset,
            String userId
    ) {
        String query = """
                SELECT follow.id, user_id, creator_id, start_date,
                    creator.username, creator.registration_date
                FROM follow
                JOIN public.user ON user_id = public.user.id
                JOIN public.user AS creator ON creator_id = creator.id
                WHERE user_id = :user_id
                    AND LOWER(creator.username) LIKE LOWER(:search_query)
                ORDER BY creator.username
                LIMIT :limit
                OFFSET :offset
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(SEARCH_QUERY_PARAM, "%" + searchQuery + "%")
                .addValue(LIMIT_PARAM, limit)
                .addValue(OFFSET_PARAM, offset)
                .addValue(USER_ID_COLUMN, userId);

        return jdbcTemplate.query(query, sqlParameterSource, new FollowRowMapper());
    }

    @Override
    public List<FollowEntity> getFollowsByUserId(String userId) {
        String query = """
                SELECT *
                FROM follow
                WHERE user_id = :user_id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(USER_ID_COLUMN, userId);

        return jdbcTemplate.query(query, sqlParameterSource, new FollowRowMapper());
    }

    @Override
    public Optional<FollowEntity> getFollowByUserAndCreatorIds(FollowRequest followRequest) {
        String query = """
                SELECT *
                FROM follow
                WHERE user_id = :user_id AND creator_id = :creator_id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID_COLUMN, followRequest.userId())
                .addValue(CREATOR_ID_COLUMN, followRequest.creatorId());

        return jdbcTemplate.query(query, sqlParameterSource, new FollowRowMapper())
                .stream()
                .findFirst();
    }

    @Override
    public Long getFollowerCountByUserId(String userId) {
        String query = """
                SELECT COUNT(*)
                FROM follow
                WHERE creator_id = :creator_id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(CREATOR_ID_COLUMN, userId);

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Long.class);
    }

    @Override
    public void insertFollow(FollowRequest followRequest) {
        String query = """
                INSERT INTO follow (user_id, creator_id)
                VALUES (:user_id, :creator_id)
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID_COLUMN, followRequest.userId())
                .addValue(CREATOR_ID_COLUMN, followRequest.creatorId());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void deleteFollowById(Long followId) {
        String query = """
                DELETE FROM follow
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, followId);

        jdbcTemplate.update(query, sqlParameterSource);
    }
}
