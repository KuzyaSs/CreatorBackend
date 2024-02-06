package ru.ermakov.creator.feature.post.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.post.model.image.Image;
import ru.ermakov.creator.feature.post.repository.PostImageDao;

import java.util.List;

@Service
public class PostImageServiceImpl implements PostImageService {
    private final PostImageDao postImageDao;

    public PostImageServiceImpl(PostImageDao postImageDao) {
        this.postImageDao = postImageDao;
    }

    @Override
    public List<Image> getImagesByPostId(Long postId) {
        return postImageDao.getImagesByPostId(postId)
                .stream()
                .map(imageEntity ->
                        new Image(
                                imageEntity.id(),
                                imageEntity.url()
                        )
                ).toList();
    }

    @Override
    public void insertImagesByPostId(List<String> imageUrls, Long postId) {
        postImageDao.deleteImagesByPostId(postId);
        postImageDao.insertImagesByPostId(imageUrls, postId);
    }
}
