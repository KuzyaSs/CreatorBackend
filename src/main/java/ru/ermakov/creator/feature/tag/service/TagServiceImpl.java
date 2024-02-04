package ru.ermakov.creator.feature.tag.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.tag.exception.DuplicateTagNameException;
import ru.ermakov.creator.feature.tag.exception.TagNotFoundException;
import ru.ermakov.creator.feature.tag.model.Tag;
import ru.ermakov.creator.feature.tag.model.TagEntity;
import ru.ermakov.creator.feature.tag.model.TagRequest;
import ru.ermakov.creator.feature.tag.repository.TagDao;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;

    private static final Long INVALID_TAG_ID = -1L;

    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> getTagsByCreatorId(String creatorId) {
        return tagDao.getTagsByCreatorId(creatorId)
                .stream()
                .map(tagEntity ->
                        new Tag(
                                tagEntity.id(),
                                tagEntity.name()
                        )
                ).toList();
    }

    @Override
    public Tag getTagById(Long tagId) {
        TagEntity tagEntity = tagDao.getTagById(tagId)
                .orElseThrow(TagNotFoundException::new);
        return new Tag(
                tagEntity.id(),
                tagEntity.name()
        );
    }

    @Override
    public void insertTag(TagRequest tagRequest) {
        if (tagDao.tagExistsByName(INVALID_TAG_ID, tagRequest)) {
            throw new DuplicateTagNameException();
        }
        tagDao.insertTag(tagRequest);
    }

    @Override
    public void updateTag(Long tagId, TagRequest tagRequest) {
        if (tagDao.tagExistsByName(tagId, tagRequest)) {
            throw new DuplicateTagNameException();
        }
        tagDao.updateTag(tagId, tagRequest);
    }

    @Override
    public void deleteTagById(Long tagId) {
        tagDao.deleteTagById(tagId);
    }
}
