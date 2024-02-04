package ru.ermakov.creator.feature.post.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.tag.model.Tag;

import java.util.List;

@Service
public class PostTagServiceImpl implements PostTagService {
    @Override
    public List<Tag> getTagsByPostId(Long postId) {
        return null;
    }

    @Override
    public void insertTagsByPostId(List<Tag> tags, Long postId) {

    }
}
