package ru.ermakov.creator.feature.postComment.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.feature.postComment.model.PostCommentEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

public class PostCommentRowMapper implements RowMapper<PostCommentEntity> {
    private static final Long INVALID_COMMENT_ID = -1L;

    @Override
    public PostCommentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long replyCommentId = rs.getLong(REPLY_COMMENT_ID_COLUMN);
        if (rs.wasNull()) {
            replyCommentId = INVALID_COMMENT_ID;
        }

        return new PostCommentEntity(
                rs.getLong(ID_COLUMN),
                rs.getString(USER_ID_COLUMN),
                rs.getLong(POST_ID_COLUMN),
                replyCommentId,
                rs.getString(CONTENT_COLUMN),
                rs.getTimestamp(PUBLICATION_DATE_COLUMN),
                rs.getBoolean(IS_EDITED_COLUMN)
        );
    }
}