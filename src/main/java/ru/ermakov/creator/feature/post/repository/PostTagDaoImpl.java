package ru.ermakov.creator.feature.post.repository;

import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.post.model.tag.PostTagEntity;
import ru.ermakov.creator.feature.tag.model.Tag;

import java.util.List;

@Repository
public class PostTagDaoImpl implements PostTagDao {
    @Override
    public List<PostTagEntity> getTagsByPostId(Long postId) {
        return null;
    }

    @Override
    public void insertTagsByPostId(List<Tag> tags, Long postId) {

    }
}
