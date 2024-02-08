package ru.ermakov.creator.feature.post.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.post.model.filter.BlogFilter;
import ru.ermakov.creator.feature.post.model.filter.FeedFilter;
import ru.ermakov.creator.feature.post.model.post.PostEntity;
import ru.ermakov.creator.feature.post.model.post.PostRequest;
import ru.ermakov.creator.feature.post.repository.mapper.PostRowMapper;

import java.util.List;
import java.util.Optional;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

@Repository
public class PostDaoImpl implements PostDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PostDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PostEntity> getFilteredPostPageByUserId(Long postId, Integer limit) {
        return null;
    }

    @Override
    public List<PostEntity> getFilteredFollowingPostPageByUserId(Long postId, Integer limit) {
        return null;
    }

    @Override
    public List<PostEntity> getFilteredPostPageByUserAndCreatorIds(String creatorId, Long postId, Integer limit) {
        return null;
    }

    @Override
    public List<PostEntity> getPostPageByUserIdAndSearchQuery(String searchQuery, Long postId, Integer limit) {
        return null;
    }

    @Override
    public Optional<PostEntity> getPostByUserAndPostIds(Long postId) {
        String query = """
                SELECT *
                FROM post
                WHERE post_id = :post_id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, postId);

        return jdbcTemplate.query(query, sqlParameterSource, new PostRowMapper())
                .stream()
                .findFirst();
    }

    @Override
    public Long insertPost(PostRequest postRequest) {
        String query = """
                INSERT INTO post (creator_id, title, content)
                VALUES (:creator_id, :title, :content)
                RETURNING ID
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(CREATOR_ID_COLUMN, postRequest.creatorId())
                .addValue(TITLE_COLUMN, postRequest.title())
                .addValue(CONTENT_COLUMN, postRequest.content());

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Long.class);
    }

    @Override
    public void updatePost(Long postId, PostRequest postRequest) {
        String query = """
                UPDATE post
                SET title = :title,
                    content = :content,
                    is_edited = true
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(TITLE_COLUMN, postRequest.title())
                .addValue(CONTENT_COLUMN, postRequest.content())
                .addValue(ID_COLUMN, postId);

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void deletePostById(Long postId) {
        String query = """
                DELETE FROM post
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, postId);

        jdbcTemplate.update(query, sqlParameterSource);
    }
}
