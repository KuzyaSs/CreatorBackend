package ru.ermakov.creator.feature.tag.service;

import ru.ermakov.creator.feature.tag.model.Tag;
import ru.ermakov.creator.feature.tag.model.TagRequest;

import java.util.List;

public interface TagService {
    List<Tag> getTagsByCreatorId(String creatorId);

    Tag getTagById(Long tagId);

    void insertTag(TagRequest tagRequest);

    void updateTag(Long tagId, TagRequest tagRequest);

    void deleteTagById(Long tagId);
}
