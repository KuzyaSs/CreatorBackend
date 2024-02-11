package ru.ermakov.creator.feature.postTag.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.feature.postTag.model.PostTagEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

public class PostTagRowMapper implements RowMapper<PostTagEntity> {
    @Override
    public PostTagEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PostTagEntity(
                rs.getLong(ID_COLUMN),
                rs.getLong(POST_ID_COLUMN),
                rs.getLong(TAG_ID_COLUMN)
        );
    }
}
