package ru.ermakov.creator.feature.post.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.post.model.like.LikeRequest;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

@Repository
public class PostLikeDaoImpl implements PostLikeDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PostLikeDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long getLikeCountByPostId(Long postId) {
        String query = """
                SELECT COUNT(*)
                FROM post_like
                WHERE post_id = :post_id
                    """;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(POST_ID_COLUMN, postId);

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Long.class);
    }

    @Override
    public void insertLike(LikeRequest likeRequest) {
        String query = """
                INSERT INTO post_like (user_id, post_id)
                VALUES (:user_id, :post_id)
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID_COLUMN, likeRequest.userId())
                .addValue(POST_ID_COLUMN, likeRequest.postId());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void deleteLike(LikeRequest likeRequest) {
        String query = """
                DELETE FROM post_like
                WHERE user_id = :user_id
                    AND post_id = :post_id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID_COLUMN, likeRequest.userId())
                .addValue(POST_ID_COLUMN, likeRequest.postId());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public Boolean isLikedPostByUserId(LikeRequest likeRequest) {
        String query = """
                SELECT EXISTS(
                    SELECT 1
                    FROM post_like
                    WHERE user_id = :user_id
                        AND post_id = :post_id
                )
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID_COLUMN, likeRequest.userId())
                .addValue(POST_ID_COLUMN, likeRequest.postId());

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Boolean.class);
    }
}
