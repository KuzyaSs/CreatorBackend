package ru.ermakov.creator.feature.post.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.post.model.tag.PostTagEntity;
import ru.ermakov.creator.feature.post.repository.PostTagDao;
import ru.ermakov.creator.feature.tag.model.Tag;
import ru.ermakov.creator.feature.tag.service.TagService;

import java.util.List;

@Service
public class PostTagServiceImpl implements PostTagService {
    private final PostTagDao postTagDao;
    private final TagService tagService;

    public PostTagServiceImpl(PostTagDao postTagDao, TagService tagService) {
        this.postTagDao = postTagDao;
        this.tagService = tagService;
    }

    @Override
    public List<Tag> getTagsByPostId(Long postId) {
        List<PostTagEntity> postTagEntities = postTagDao.getTagsByPostId(postId);
        return postTagEntities
                .stream()
                .map(postTagEntity ->
                        tagService.getTagById(postTagEntity.tagId())
                ).toList();
    }

    @Override
    public void insertTagsByPostId(List<Tag> tags, Long postId) {
        postTagDao.deleteTagsByPostId(postId);
        postTagDao.insertTagsByPostId(tags, postId);
    }
}
