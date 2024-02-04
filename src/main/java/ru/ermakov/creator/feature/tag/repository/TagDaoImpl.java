package ru.ermakov.creator.feature.tag.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.subscription.repository.mapper.SubscriptionRowMapper;
import ru.ermakov.creator.feature.tag.model.TagEntity;
import ru.ermakov.creator.feature.tag.model.TagRequest;
import ru.ermakov.creator.feature.tag.repository.mapper.TagRowMapper;

import java.util.List;
import java.util.Optional;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;
import static ru.ermakov.creator.feature.shared.ParamNameConstants.PRICE_COLUMN;

@Repository
public class TagDaoImpl implements TagDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TagDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TagEntity> getTagsByCreatorId(String creatorId) {
        String query = """
                SELECT *
                FROM tag
                WHERE creator_id = :creator_id
                ORDER BY name ASC
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(CREATOR_ID_COLUMN, creatorId);

        return jdbcTemplate.query(query, sqlParameterSource, new TagRowMapper());
    }

    @Override
    public Optional<TagEntity> getTagById(Long tagId) {
        String query = """
                SELECT *
                FROM tag
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, tagId);

        return jdbcTemplate.query(query, sqlParameterSource, new TagRowMapper())
                .stream()
                .findFirst();
    }

    @Override
    public void insertTag(TagRequest tagRequest) {
        String query = """
                INSERT INTO tag (creator_id, name)
                VALUES (:creator_id, :name)
                     """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(CREATOR_ID_COLUMN, tagRequest.creatorId())
                .addValue(NAME_COLUMN, tagRequest.name());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void updateTag(Long tagId, TagRequest tagRequest) {
        String query = """
                UPDATE tag
                SET name = :name
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, tagId)
                .addValue(NAME_COLUMN, tagRequest.name());

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public void deleteTagById(Long tagId) {
        String query = """
                DELETE FROM tag
                WHERE id = :id
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(ID_COLUMN, tagId);

        jdbcTemplate.update(query, sqlParameterSource);
    }

    @Override
    public Boolean tagExistsByName(Long tagId, TagRequest tagRequest) {
        String query = """
                SELECT EXISTS(
                    SELECT 1
                    FROM tag
                    WHERE id != :id
                        AND creator_id = :creator_id
                        AND name = :name
                )
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, tagId)
                .addValue(CREATOR_ID_COLUMN, tagRequest.creatorId())
                .addValue(NAME_COLUMN, tagRequest.name());

        return jdbcTemplate.queryForObject(query, sqlParameterSource, Boolean.class);
    }
}
