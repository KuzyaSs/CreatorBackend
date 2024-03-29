package ru.ermakov.creator.feature.postTag.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.postTag.model.PostTagEntity;
import ru.ermakov.creator.feature.postTag.repository.mapper.PostTagRowMapper;

import java.util.List;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.POST_ID_COLUMN;
import static ru.ermakov.creator.feature.shared.ParamNameConstants.TAG_ID_COLUMN;

@Repository
public class PostTagDaoImpl implements PostTagDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PostTagDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PostTagEntity> getTagsByPostId(Long postId) {
        String query = """
                SELECT *
                FROM post_tag
                WHERE post_id = :post_id
                ORDER BY tag_id ASC
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(POST_ID_COLUMN, postId);

        return jdbcTemplate.query(query, sqlParameterSource, new PostTagRowMapper());
    }

    @Override
    public void insertTagsByPostId(List<Long> tagIds, Long postId) {
        String query = """
                INSERT INTO post_tag (post_id, tag_id)
                VALUES (:post_id, :tag_id)
                     """;

        SqlParameterSource[] sqlParameterSources = tagIds
                .stream()
                .map(tagId ->
                        new MapSqlParameterSource()
                                .addValue(POST_ID_COLUMN, postId)
                                .addValue(TAG_ID_COLUMN, tagId)
                ).toArray(SqlParameterSource[]::new);

        jdbcTemplate.batchUpdate(query, sqlParameterSources);
    }

    @Override
    public void deleteTagsByPostId(Long postId) {
        String query = """
                DELETE FROM post_tag
                WHERE post_id = :post_id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(POST_ID_COLUMN, postId);

        jdbcTemplate.update(query, sqlParameterSource);
    }
}
