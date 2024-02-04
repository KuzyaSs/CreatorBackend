package ru.ermakov.creator.feature.post.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.post.model.image.Image;

import java.util.List;

@Service
public class PostImageServiceImpl implements PostImageService {
    @Override
    public List<Image> getImagesByPostId(Long postId) {
        return null;
    }

    @Override
    public void insertImagesByPostId(List<String> imageUrls, Long postId) {

    }
}
