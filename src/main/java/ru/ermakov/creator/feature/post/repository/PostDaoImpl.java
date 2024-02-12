package ru.ermakov.creator.feature.post.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.post.model.PostEntity;
import ru.ermakov.creator.feature.post.model.PostRequest;
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
    public List<PostEntity> getFilteredPostPageByUserId(
            String userId,
            List<String> followedCreatorIds,
            List<Long> selectedCategoryIds,
            Boolean isEverything,
            List<Long> purchasedSubscriptionIds,
            Boolean isOnlyAllowed,
            Long postId,
            Integer limit
    ) {
        String query = """
                SELECT DISTINCT ON (p.id) p.id, p.creator_id, p.title, p.content, p.publication_date, p.is_edited
                FROM post AS p
                LEFT JOIN post_subscription AS ps ON p.id = ps.post_id
                WHERE p.id < :id
                    AND p.creator_id NOT IN (:creator_id)
                    AND p.creator_id != :user_id
                    AND (p.creator_id IN (
                        SELECT user_id
                        FROM user_category
                        WHERE category_id IN (:category_id) AND is_selected = TRUE
                    ) OR :is_everything)
                    AND (ps.subscription_id IS NULL OR ps.subscription_id IN (:subscription_id) OR NOT :is_only_allowed)
                ORDER BY p.id DESC
                LIMIT :limit
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, postId)
                .addValue(CREATOR_ID_COLUMN, followedCreatorIds)
                .addValue(CATEGORY_ID_COLUMN, selectedCategoryIds)
                .addValue(IS_EVERYTHING_PARAM, isEverything)
                .addValue(USER_ID_COLUMN, userId)
                .addValue(SUBSCRIPTION_ID_COLUMN, purchasedSubscriptionIds)
                .addValue(IS_ONLY_ALLOWED_PARAM, isOnlyAllowed)
                .addValue(LIMIT_PARAM, limit);

        return jdbcTemplate.query(query, sqlParameterSource, new PostRowMapper());
    }

    @Override
    public List<PostEntity> getFilteredFollowingPostPageByUserId(
            String userId,
            List<String> followedCreatorIds,
            List<Long> selectedCategoryIds,
            Boolean isEverything,
            List<Long> purchasedSubscriptionIds,
            Boolean isOnlyAllowed,
            Long postId,
            Integer limit
    ) {
        String query = """
                SELECT DISTINCT ON (p.id) p.id, p.creator_id, p.title, p.content, p.publication_date, p.is_edited
                FROM post AS p
                LEFT JOIN post_subscription AS ps ON p.id = ps.post_id
                WHERE p.id < :id
                    AND p.creator_id IN (:creator_id)
                    AND p.creator_id != :user_id
                    AND (p.creator_id IN (
                        SELECT user_id
                        FROM user_category
                        WHERE category_id IN (:category_id) AND is_selected = TRUE
                    ) OR :is_everything)
                    AND (ps.subscription_id IS NULL OR ps.subscription_id IN (:subscription_id) OR NOT :is_only_allowed)
                ORDER BY p.id DESC
                LIMIT :limit
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, postId)
                .addValue(CREATOR_ID_COLUMN, followedCreatorIds)
                .addValue(CATEGORY_ID_COLUMN, selectedCategoryIds)
                .addValue(IS_EVERYTHING_PARAM, isEverything)
                .addValue(USER_ID_COLUMN, userId)
                .addValue(SUBSCRIPTION_ID_COLUMN, purchasedSubscriptionIds)
                .addValue(IS_ONLY_ALLOWED_PARAM, isOnlyAllowed)
                .addValue(LIMIT_PARAM, limit);

        return jdbcTemplate.query(query, sqlParameterSource, new PostRowMapper());
    }

    @Override
    public List<PostEntity> getFilteredPostPageByCreatorId(
            String creatorId,
            List<Long> selectedTagIds,
            Boolean isEverything,
            List<Long> purchasedSubscriptionIds,
            Boolean isOnlyAllowed,
            Long postId,
            Integer limit
    ) {
        String query = """
                SELECT DISTINCT ON (p.id) p.id, p.creator_id, p.title, p.content, p.publication_date, p.is_edited
                FROM post AS p
                LEFT JOIN post_tag AS pt ON p.id = pt.post_id
                LEFT JOIN post_subscription AS ps ON p.id = ps.post_id
                WHERE p.id < :id
                    AND p.creator_id = :creator_id
                    AND (pt.tag_id IN (:tag_id) OR :is_everything)
                    AND (ps.subscription_id IS NULL OR ps.subscription_id IN (:subscription_id) OR NOT :is_only_allowed)
                ORDER BY p.id DESC
                LIMIT :limit
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, postId)
                .addValue(CREATOR_ID_COLUMN, creatorId)
                .addValue(TAG_ID_COLUMN, selectedTagIds)
                .addValue(IS_EVERYTHING_PARAM, isEverything)
                .addValue(SUBSCRIPTION_ID_COLUMN, purchasedSubscriptionIds)
                .addValue(IS_ONLY_ALLOWED_PARAM, isOnlyAllowed)
                .addValue(LIMIT_PARAM, limit);

        return jdbcTemplate.query(query, sqlParameterSource, new PostRowMapper());
    }

    @Override
    public List<PostEntity> getPostPageByUserIdAndSearchQuery(String searchQuery, Long postId, Integer limit) {
        String query = """
                SELECT *
                FROM post
                WHERE id < :id
                    AND LOWER(title) LIKE LOWER(:search_query)
                ORDER BY id DESC
                LIMIT :limit
                    """;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue(ID_COLUMN, postId)
                .addValue(SEARCH_QUERY_PARAM, "%" + searchQuery + "%")
                .addValue(LIMIT_PARAM, limit);

        return jdbcTemplate.query(query, sqlParameterSource, new PostRowMapper());
    }

    @Override
    public Optional<PostEntity> getPostById(Long postId) {
        String query = """
                SELECT *
                FROM post
                WHERE id = :id
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
