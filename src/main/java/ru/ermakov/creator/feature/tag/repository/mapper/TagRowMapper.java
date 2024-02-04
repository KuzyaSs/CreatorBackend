package ru.ermakov.creator.feature.tag.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ermakov.creator.feature.tag.model.TagEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.ermakov.creator.feature.shared.ParamNameConstants.*;

public class TagRowMapper implements RowMapper<TagEntity> {
    @Override
    public TagEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TagEntity(
                rs.getLong(ID_COLUMN),
                rs.getString(CREATOR_ID_COLUMN),
                rs.getString(NAME_COLUMN)
        );
    }
}
