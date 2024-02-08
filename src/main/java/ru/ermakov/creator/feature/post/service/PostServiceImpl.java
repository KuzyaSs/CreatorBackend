package ru.ermakov.creator.feature.post.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.creator.service.CreatorService;
import ru.ermakov.creator.feature.post.exception.PostNotFoundException;
import ru.ermakov.creator.feature.post.model.filter.BlogFilter;
import ru.ermakov.creator.feature.post.model.filter.FeedFilter;
import ru.ermakov.creator.feature.post.model.like.LikeRequest;
import ru.ermakov.creator.feature.post.model.post.Post;
import ru.ermakov.creator.feature.post.model.post.PostEntity;
import ru.ermakov.creator.feature.post.model.post.PostRequest;
import ru.ermakov.creator.feature.post.repository.PostDao;
import ru.ermakov.creator.feature.subscription.model.Subscription;
import ru.ermakov.creator.feature.userSubscription.service.UserSubscriptionService;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostDao postDao;
    private final CreatorService creatorService;
    private final UserSubscriptionService userSubscriptionService;
    private final PostImageService postImageService;
    private final PostTagService postTagService;
    private final PostSubscriptionService postSubscriptionService;
    private final PostLikeService postLikeService;

    private static final String ALL_POST_TYPE = "ALL";
    private static final String AVAILABLE_POST_TYPE = "AVAILABLE";

    public PostServiceImpl(
            PostDao postDao,
            CreatorService creatorService,
            UserSubscriptionService userSubscriptionService, PostImageService postImageService,
            PostTagService postTagService,
            PostSubscriptionService postSubscriptionService,
            PostLikeService postLikeService
    ) {
        this.postDao = postDao;
        this.creatorService = creatorService;
        this.userSubscriptionService = userSubscriptionService;
        this.postImageService = postImageService;
        this.postTagService = postTagService;
        this.postSubscriptionService = postSubscriptionService;
        this.postLikeService = postLikeService;
    }

    @Override
    public List<Post> getFilteredPostPageByUserId(String userId, FeedFilter feedFilter, Long postId, Integer limit) {
        return postDao.getFilteredPostPageByUserId(postId, limit)
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
                                userSubscriptionService.isUserSubscribedBySubscriptionIds(
                                        userId,
                                        postSubscriptionService.getSubscriptionsByPostId(
                                                postEntity.id()
                                        ).stream().map(Subscription::id).toList()
                                ),
                                postLikeService.isLikedPostByUserId(new LikeRequest(userId, postEntity.id())),
                                postEntity.isEdited()
                        )
                ).toList();
    }

    @Override
    public List<Post> getFilteredFollowingPostPageByUserId(String userId, FeedFilter feedFilter, Long postId, Integer limit) {
        return postDao.getFilteredFollowingPostPageByUserId(postId, limit)
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
                                userSubscriptionService.isUserSubscribedBySubscriptionIds(
                                        userId,
                                        postSubscriptionService.getSubscriptionsByPostId(
                                                postEntity.id()
                                        ).stream().map(Subscription::id).toList()
                                ),
                                postLikeService.isLikedPostByUserId(new LikeRequest(userId, postEntity.id())),
                                postEntity.isEdited()
                        )
                ).toList();
    }

    @Override
    public List<Post> getFilteredPostPageByUserAndCreatorIds(String userId, String creatorId, BlogFilter blogFilter, Long postId, Integer limit) {
        // Refactor the filter later.
        return postDao.getFilteredPostPageByUserAndCreatorIds(creatorId, postId, limit)
                .stream()
                .filter(postEntity ->
                        (!postTagService.getTagsByPostId(postEntity.id())
                                .stream()
                                .filter(tagId ->
                                        blogFilter.tags().contains(tagId)
                                ).toList().isEmpty() || blogFilter.tags().isEmpty()) && (blogFilter.postType().equals(ALL_POST_TYPE) || userSubscriptionService.isUserSubscribedBySubscriptionIds(
                                userId,
                                postSubscriptionService.getSubscriptionsByPostId(
                                        postEntity.id()
                                ).stream().map(Subscription::id).toList()
                        ))
                )
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
                                userSubscriptionService.isUserSubscribedBySubscriptionIds(
                                        userId,
                                        postSubscriptionService.getSubscriptionsByPostId(
                                                postEntity.id()
                                        ).stream().map(Subscription::id).toList()
                                ),
                                postLikeService.isLikedPostByUserId(new LikeRequest(userId, postEntity.id())),
                                postEntity.isEdited()
                        )
                ).toList();
    }

    @Override
    public List<Post> getPostPageByUserIdAndSearchQuery(String userId, String searchQuery, Long postId, Integer limit) {
        return postDao.getPostPageByUserIdAndSearchQuery(searchQuery, postId, limit)
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
                                userSubscriptionService.isUserSubscribedBySubscriptionIds(
                                        userId,
                                        postSubscriptionService.getSubscriptionsByPostId(
                                                postEntity.id()
                                        ).stream().map(Subscription::id).toList()
                                ),
                                postLikeService.isLikedPostByUserId(new LikeRequest(userId, postEntity.id())),
                                postEntity.isEdited()
                        )
                ).toList();
    }

    @Override
    public Post getPostByUserAndPostIds(String userId, Long postId) {
        PostEntity postEntity = postDao.getPostByUserAndPostIds(postId).orElseThrow(PostNotFoundException::new);
        List<Subscription> requiredSubscriptions = postSubscriptionService.getSubscriptionsByPostId(postEntity.id());

        return new Post(
                postEntity.id(),
                creatorService.getCreatorByUserId(postEntity.creatorId()),
                postEntity.title(),
                postEntity.content(),
                postImageService.getImagesByPostId(postEntity.id()),
                postTagService.getTagsByPostId(postEntity.id()),
                requiredSubscriptions,
                postLikeService.getLikeCountByPostId(postEntity.id()),
                postEntity.publicationDate(),
                userSubscriptionService.isUserSubscribedBySubscriptionIds(
                        userId,
                        requiredSubscriptions.stream().map(Subscription::id).toList()
                ),
                postLikeService.isLikedPostByUserId(new LikeRequest(userId, postEntity.id())),
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
