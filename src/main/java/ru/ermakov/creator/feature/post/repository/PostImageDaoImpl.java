package ru.ermakov.creator.feature.post.repository;

import org.springframework.stereotype.Repository;
import ru.ermakov.creator.feature.post.model.image.ImageEntity;

import java.util.List;

@Repository
public class PostImageDaoImpl implements PostImageDao {
    @Override
    public List<ImageEntity> getImagesByPostId(Long postId) {
        return null;
    }

    @Override
    public void insertImagesByPostId(List<String> imageUrls, Long postId) {

    }
}
