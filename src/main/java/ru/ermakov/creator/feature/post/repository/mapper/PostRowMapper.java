package ru.ermakov.creator.feature.post.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.feature.post.model.PostEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

public class PostRowMapper implements RowMapper<PostEntity> {
    @Override
    public PostEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PostEntity(
                rs.getLong(ID_COLUMN),
                rs.getString(CREATOR_ID_COLUMN),
                rs.getString(TITLE_COLUMN),
                rs.getString(CONTENT_COLUMN),
                rs.getTimestamp(PUBLICATION_DATE_COLUMN),
                rs.getBoolean(IS_EDITED_COLUMN)
        );
    }
}
