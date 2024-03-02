package ru.ermakov.creator.feature.postComment.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.postComment.model.CommentRequest;
import ru.ermakov.creator.feature.postComment.model.PostCommentEntity;
import ru.ermakov.creator.feature.postComment.repository.mapper.PostCommentRowMapper;

import java.util.List;
import java.util.Optional;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

@Repository
public class PostCommentDaoImpl implements PostCommentDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PostCommentDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PostCommentEntity> getCommentPageByPostId(Long postId, Long replyCommentId, Long commentId, Integer limit) {
        String query = """
                SELECT *
                FROM post_comment
                WHERE id < :id
                    AND post_id = :post_id
                    AND (reply_comment_id = :reply_comment_id
                    OR reply_comment_id IS NULL AND :reply_comment_id = -1)
                ORDER BY id DESC
                LIMIT :limit
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, commentId)
                .addValue(POST_ID_COLUMN, postId)
                .addValue(REPLY_COMMENT_ID_COLUMN, replyCommentId)
                .addValue(LIMIT_PARAM, limit);

        return jdbcTemplate.query(query, sqlParameterSource, new PostCommentRowMapper());
    }

    @Override
    public Optional<PostCommentEntity> getCommentById(Long commentId) {
        String query = """
                SELECT *
                FROM post_comment
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, commentId);

        return jdbcTemplate.query(query, sqlParameterSource, new PostCommentRowMapper())
                .stream()
                .findFirst();
    }

    @Override
    public Long getCommentCountByPostId(Long postId) {
        String query = """
                SELECT COUNT(*)
                FROM post_comment
                WHERE post_id = :post_id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(POST_ID_COLUMN, postId);

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Long.class);
    }

    @Override
    public void insertComment(CommentRequest commentRequest) {
        String query = """
                INSERT INTO post_comment (user_id, post_id, reply_comment_id, content)
                VALUES (:user_id, :post_id, :reply_comment_id, :content)
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(USER_ID_COLUMN, commentRequest.userId())
                .addValue(POST_ID_COLUMN, commentRequest.postId())
                .addValue(REPLY_COMMENT_ID_COLUMN, commentRequest.replyCommentId())
                .addValue(CONTENT_COLUMN, commentRequest.content());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void updateComment(Long commentId, CommentRequest commentRequest) {
        String query = """
                UPDATE post_comment
                SET content = :content,
                    is_edited = true
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(CONTENT_COLUMN, commentRequest.content())
                .addValue(ID_COLUMN, commentId);

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void deleteCommentById(Long commentId) {
        String query = """
                DELETE FROM post_comment
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, commentId);

        jdbcTemplate.update(query, sqlParameterSource);
    }
}
