package ru.ermakov.creator.feature.postCommentLike.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.postCommentLike.model.PostCommentLikeRequest;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

@Repository
public class PostCommentLikeDaoImpl implements PostCommentLikeDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PostCommentLikeDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long getPostCommentLikeCountByPostCommentId(Long postCommentId) {
        String query = """
                SELECT COUNT(*)
                FROM post_comment_like
                WHERE post_comment_id = :post_comment_id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(
                POST_COMMENT_ID_COLUMN,
                postCommentId
        );

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Long.class);
    }

    @Override
    public void insertPostCommentLike(PostCommentLikeRequest postCommentLikeRequest) {
        String query = """
                INSERT INTO post_comment_like (user_id, post_comment_id)
                VALUES (:user_id, :post_comment_id)
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID_COLUMN, postCommentLikeRequest.userId())
                .addValue(POST_COMMENT_ID_COLUMN, postCommentLikeRequest.postCommentId());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void deletePostCommentLike(PostCommentLikeRequest postCommentLikeRequest) {
        String query = """
                DELETE FROM post_comment_like
                WHERE user_id = :user_id
                    AND post_comment_id = :post_comment_id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID_COLUMN, postCommentLikeRequest.userId())
                .addValue(POST_COMMENT_ID_COLUMN, postCommentLikeRequest.postCommentId());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public Boolean isLikedPostComment(PostCommentLikeRequest postCommentLikeRequest) {
        String query = """
                SELECT EXISTS(
                    SELECT 1
                    FROM post_comment_like
                    WHERE user_id = :user_id
                        AND post_comment_id = :post_comment_id
                )
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID_COLUMN, postCommentLikeRequest.userId())
                .addValue(POST_COMMENT_ID_COLUMN, postCommentLikeRequest.postCommentId());

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Boolean.class);
    }
}