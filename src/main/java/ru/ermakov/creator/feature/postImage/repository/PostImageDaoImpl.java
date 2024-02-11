package ru.ermakov.creator.feature.postImage.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.postImage.model.PostImageEntity;
import ru.ermakov.creator.feature.postImage.repository.mapper.PostImageRowMapper;

import java.util.List;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

@Repository
public class PostImageDaoImpl implements PostImageDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PostImageDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PostImageEntity> getImagesByPostId(Long postId) {
        String query = """
                SELECT *
                FROM post_image
                WHERE post_id = :post_id
                ORDER BY id ASC
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(POST_ID_COLUMN, postId);

        return jdbcTemplate.query(query, sqlParameterSource, new PostImageRowMapper());
    }

    @Override
    public void insertImagesByPostId(List<String> imageUrls, Long postId) {
        String query = """
                INSERT INTO post_image (post_id, url)
                VALUES (:post_id, :url)
                     """;

        SqlParameterSource[] sqlParameterSources = imageUrls
                .stream()
                .map(imageUrl ->
                        new MapSqlParameterSource()
                                .addValue(POST_ID_COLUMN, postId)
                                .addValue(URL_COLUMN, imageUrl)
                ).toArray(SqlParameterSource[]::new);

        jdbcTemplate.batchUpdate(query, sqlParameterSources);
    }

    @Override
    public void deleteImagesByPostId(Long postId) {
        String query = """
                DELETE FROM post_image
                WHERE post_id = :post_id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(POST_ID_COLUMN, postId);

        jdbcTemplate.update(query, sqlParameterSource);
    }
}
