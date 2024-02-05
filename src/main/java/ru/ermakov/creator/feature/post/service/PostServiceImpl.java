package ru.ermakov.creator.feature.post.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.creator.service.CreatorService;
import ru.ermakov.creator.feature.post.exception.PostNotFoundException;
import ru.ermakov.creator.feature.post.model.filter.BlogFilter;
import ru.ermakov.creator.feature.post.model.filter.FeedFilter;
import ru.ermakov.creator.feature.post.model.post.Post;
import ru.ermakov.creator.feature.post.model.post.PostEntity;
import ru.ermakov.creator.feature.post.model.post.PostRequest;
import ru.ermakov.creator.feature.post.repository.PostDao;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostDao postDao;
    private final CreatorService creatorService;
    private final PostImageService postImageService;
    private final PostTagService postTagService;
    private final PostSubscriptionService postSubscriptionService;
    private final PostLikeService postLikeService;

    public PostServiceImpl(
            PostDao postDao,
            CreatorService creatorService,
            PostImageService postImageService,
            PostTagService postTagService,
            PostSubscriptionService postSubscriptionService,
            PostLikeService postLikeService
    ) {
        this.postDao = postDao;
        this.creatorService = creatorService;
        this.postImageService = postImageService;
        this.postTagService = postTagService;
        this.postSubscriptionService = postSubscriptionService;
        this.postLikeService = postLikeService;
    }

    @Override
    public List<Post> getFilteredPostPage(FeedFilter feedFilter, Long postId, Integer limit) {
        return postDao.getFilteredPostPage(feedFilter, postId, limit)
                .stream()
                .map(postEntity ->
                        new Post(
                                postEntity.id(),
                                creatorService.getCreatorByUserId(postEntity.creatorId()),
                                postEntity.title(),
                                postEntity.content(),
                                postImageService.getImagesByPostId(postEntity.id()),
                                postTagService.getTagsByPostId(postEntity.id()),
                                postSubscriptionService.getSubscriptionsByPostId(postEntity.id()),
                                postLikeService.getLikeCountByPostId(postEntity.id()),
                                postEntity.publicationDate(),
                                postEntity.isEdited()
                        )
                ).toList();
    }

    @Override
    public List<Post> getFilteredPostPageByUserId(String userId, FeedFilter feedFilter, Long postId, Integer limit) {
        return postDao.getFilteredPostPageByUserId(userId, feedFilter, postId, limit)
                .stream()
                .map(postEntity ->
                        new Post(
                                postEntity.id(),
                                creatorService.getCreatorByUserId(postEntity.creatorId()),
                                postEntity.title(),
                                postEntity.content(),
                                postImageService.getImagesByPostId(postEntity.id()),
                                postTagService.getTagsByPostId(postEntity.id()),
                                postSubscriptionService.getSubscriptionsByPostId(postEntity.id()),
                                postLikeService.getLikeCountByPostId(postEntity.id()),
                                postEntity.publicationDate(),
                                postEntity.isEdited()
                        )
                ).toList();
    }

    @Override
    public List<Post> getFilteredPostPageByCreatorId(String creatorId, BlogFilter blogFilter, Long postId, Integer limit) {
        return postDao.getFilteredPostPageByCreatorId(creatorId, blogFilter, postId, limit)
                .stream()
                .map(postEntity ->
                        new Post(
                                postEntity.id(),
                                creatorService.getCreatorByUserId(postEntity.creatorId()),
                                postEntity.title(),
                                postEntity.content(),
                                postImageService.getImagesByPostId(postEntity.id()),
                                postTagService.getTagsByPostId(postEntity.id()),
                                postSubscriptionService.getSubscriptionsByPostId(postEntity.id()),
                                postLikeService.getLikeCountByPostId(postEntity.id()),
                                postEntity.publicationDate(),
                                postEntity.isEdited()
                        )
                ).toList();
    }

    @Override
    public List<Post> getPostPageBySearchQuery(String searchQuery, Long postId, Integer limit) {
        return postDao.getPostPageBySearchQuery(searchQuery, postId, limit)
                .stream()
                .map(postEntity ->
                        new Post(
                                postEntity.id(),
                                creatorService.getCreatorByUserId(postEntity.creatorId()),
                                postEntity.title(),
                                postEntity.content(),
                                postImageService.getImagesByPostId(postEntity.id()),
                                postTagService.getTagsByPostId(postEntity.id()),
                                postSubscriptionService.getSubscriptionsByPostId(postEntity.id()),
                                postLikeService.getLikeCountByPostId(postEntity.id()),
                                postEntity.publicationDate(),
                                postEntity.isEdited()
                        )
                ).toList();
    }

    @Override
    public Post getPostById(Long postId) {
        PostEntity postEntity = postDao.getPostById(postId).orElseThrow(PostNotFoundException::new);
        return new Post(
                postEntity.id(),
                creatorService.getCreatorByUserId(postEntity.creatorId()),
                postEntity.title(),
                postEntity.content(),
                postImageService.getImagesByPostId(postEntity.id()),
                postTagService.getTagsByPostId(postEntity.id()),
                postSubscriptionService.getSubscriptionsByPostId(postEntity.id()),
                postLikeService.getLikeCountByPostId(postEntity.id()),
                postEntity.publicationDate(),
                postEntity.isEdited()
        );
    }

    @Override
    public void insertPost(PostRequest postRequest) {
        Long postId = postDao.insertPost(postRequest);
        postImageService.insertImagesByPostId(postRequest.imageUrls(), postId);
        postTagService.insertTagsByPostId(postRequest.tags(), postId);
        postSubscriptionService.insertSubscriptionsByPostId(postRequest.requiredSubscriptions(), postId);
    }

    @Override
    public void updatePost(Long postId, PostRequest postRequest) {
        postDao.updatePost(postId, postRequest);
        postImageService.insertImagesByPostId(postRequest.imageUrls(), postId);
        postTagService.insertTagsByPostId(postRequest.tags(), postId);
        postSubscriptionService.insertSubscriptionsByPostId(postRequest.requiredSubscriptions(), postId);
    }

    @Override
    public void deletePostById(Long postId) {
        postDao.deletePostById(postId);
    }
}