package ru.ermakov.creator.feature.tag.repository;

import ru.ermakov.creator.feature.tag.model.TagEntity;
import ru.ermakov.creator.feature.tag.model.TagRequest;

import java.util.List;
import java.util.Optional;

public interface TagDao {
    List<TagEntity> getTagsByCreatorId(String creatorId);

    Optional<TagEntity> getTagById(Long tagId);

    void insertTag(TagRequest tagRequest);

    void updateTag(Long tagId, TagRequest tagRequest);

    void deleteTagById(Long tagId);

    Boolean tagExistsByName(Long tagId, TagRequest tagRequest);
}
