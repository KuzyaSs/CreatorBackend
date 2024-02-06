package ru.ermakov.creator.feature.post.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.feature.post.model.image.PostImageEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

public class PostImageRowMapper implements RowMapper<PostImageEntity> {
    @Override
    public PostImageEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PostImageEntity(
                rs.getLong(ID_COLUMN),
                rs.getLong(POST_ID_COLUMN),
                rs.getString(URL_COLUMN)
        );
    }
}
