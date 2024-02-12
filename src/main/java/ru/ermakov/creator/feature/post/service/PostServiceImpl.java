package ru.ermakov.creator.feature.post.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.creator.service.CreatorService;
import ru.ermakov.creator.feature.follow.service.FollowService;
import ru.ermakov.creator.feature.post.exception.PostNotFoundException;
import ru.ermakov.creator.feature.postLike.model.LikeRequest;
import ru.ermakov.creator.feature.post.model.Post;
import ru.ermakov.creator.feature.post.model.PostEntity;
import ru.ermakov.creator.feature.post.model.PostRequest;
import ru.ermakov.creator.feature.post.repository.PostDao;
import ru.ermakov.creator.feature.postImage.service.PostImageService;
import ru.ermakov.creator.feature.postLike.service.PostLikeService;
import ru.ermakov.creator.feature.postSubscription.service.PostSubscriptionService;
import ru.ermakov.creator.feature.postTag.service.PostTagService;
import ru.ermakov.creator.feature.subscription.model.Subscription;
import ru.ermakov.creator.feature.userSubscription.service.UserSubscriptionService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostDao postDao;
    private final CreatorService creatorService;
    private final UserSubscriptionService userSubscriptionService;
    private final FollowService followService;
    private final PostImageService postImageService;
    private final PostTagService postTagService;
    private final PostSubscriptionService postSubscriptionService;
    private final PostLikeService postLikeService;

    private static final String AVAILABLE_POST_TYPE = "AVAILABLE";
    private static final Long INVALID_ID = -1L;

    public PostServiceImpl(
            PostDao postDao,
            CreatorService creatorService,
            UserSubscriptionService userSubscriptionService, FollowService followService, PostImageService postImageService,
            PostTagService postTagService,
            PostSubscriptionService postSubscriptionService,
            PostLikeService postLikeService
    ) {
        this.postDao = postDao;
        this.creatorService = creatorService;
        this.userSubscriptionService = userSubscriptionService;
        this.followService = followService;
        this.postImageService = postImageService;
        this.postTagService = postTagService;
        this.postSubscriptionService = postSubscriptionService;
        this.postLikeService = postLikeService;
    }

    @Override
    public List<Post> getFilteredPostPageByUserId(
            String userId,
            String postType,
            List<Long> categoryIds,
            Long postId,
            Integer limit
    ) {
        List<String> followedCreatorIds = new ArrayList<>(followService.getFollowsByUserId(userId)
                .stream()
                .map(follow ->
                        follow.creator().user().id()
                )
                .toList());
        // For the PostDao (IN) to work correctly.
        followedCreatorIds.add(INVALID_ID.toString());

        List<Long> selectedCategoryIds = new ArrayList<>(categoryIds);
        // For the PostDao (IN) to work correctly.
        selectedCategoryIds.add(INVALID_ID);

        List<Long> purchasedSubscriptionIds = new ArrayList<>();
        // For the PostDao (IN) to work correctly.
        purchasedSubscriptionIds.add(INVALID_ID);

        if (postType.equals(AVAILABLE_POST_TYPE)) {
            purchasedSubscriptionIds.addAll(userSubscriptionService.getUserSubscriptionsByUserId(userId)
                    .stream()
                    .map(userSubscription ->
                            userSubscription.subscription().id()
                    ).toList());
        }

        return postDao.getFilteredPostPageByUserId(
                        userId,
                        followedCreatorIds,
                        selectedCategoryIds,
                        categoryIds.isEmpty(),
                        purchasedSubscriptionIds,
                        postType.equals(AVAILABLE_POST_TYPE),
                        postId,
                        limit
                )
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
                                postEntity.creatorId().equals(userId) || userSubscriptionService.isUserSubscribedBySubscriptionIds(
                                        userId,
                                        postSubscriptionService.getSubscriptionsByPostId(
                                                postEntity.id()
                                        ).stream().map(Subscription::id).toList()
                                ),
                                postLikeService.isLikedPost(new LikeRequest(userId, postEntity.id())),
                                postEntity.isEdited()
                        )
                ).toList();
    }

    @Override
    public List<Post> getFilteredFollowingPostPageByUserId(
            String userId,
            String postType,
            List<Long> categoryIds,
            Long postId,
            Integer limit
    ) {
        List<String> followedCreatorIds = new ArrayList<>(followService.getFollowsByUserId(userId)
                .stream()
                .map(follow ->
                        follow.creator().user().id()
                )
                .toList());
        // For the PostDao (IN) to work correctly.
        followedCreatorIds.add(INVALID_ID.toString());

        List<Long> selectedCategoryIds = new ArrayList<>(categoryIds);
        // For the PostDao (IN) to work correctly.
        selectedCategoryIds.add(INVALID_ID);

        List<Long> purchasedSubscriptionIds = new ArrayList<>();
        // For the PostDao (IN) to work correctly.
        purchasedSubscriptionIds.add(INVALID_ID);

        if (postType.equals(AVAILABLE_POST_TYPE)) {
            purchasedSubscriptionIds.addAll(userSubscriptionService.getUserSubscriptionsByUserId(userId)
                    .stream()
                    .map(userSubscription ->
                            userSubscription.subscription().id()
                    ).toList());
        }

        return postDao.getFilteredFollowingPostPageByUserId(
                        userId,
                        followedCreatorIds,
                        selectedCategoryIds,
                        categoryIds.isEmpty(),
                        purchasedSubscriptionIds,
                        postType.equals(AVAILABLE_POST_TYPE),
                        postId,
                        limit
                )
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
                                postEntity.creatorId().equals(userId) || userSubscriptionService.isUserSubscribedBySubscriptionIds(
                                        userId,
                                        postSubscriptionService.getSubscriptionsByPostId(
                                                postEntity.id()
                                        ).stream().map(Subscription::id).toList()
                                ),
                                postLikeService.isLikedPost(new LikeRequest(userId, postEntity.id())),
                                postEntity.isEdited()
                        )
                ).toList();
    }

    @Override
    public List<Post> getFilteredPostPageByUserAndCreatorIds(
            String userId,
            String creatorId,
            String postType,
            List<Long> tagIds,
            Long postId,
            Integer limit
    ) {
        List<Long> selectedTagIds = new ArrayList<>(tagIds);
        // For the PostDao (IN) to work correctly.
        selectedTagIds.add(INVALID_ID);

        List<Long> purchasedSubscriptionIds = new ArrayList<>();
        // For the PostDao (IN) to work correctly.
        purchasedSubscriptionIds.add(INVALID_ID);

        if (postType.equals(AVAILABLE_POST_TYPE)) {
            purchasedSubscriptionIds.addAll(userSubscriptionService.getUserSubscriptionsByUserId(userId)
                    .stream()
                    .map(userSubscription ->
                            userSubscription.subscription().id()
                    ).toList());
        }

        return postDao.getFilteredPostPageByCreatorId(
                        creatorId,
                        selectedTagIds,
                        tagIds.isEmpty(),
                        purchasedSubscriptionIds,
                        postType.equals(AVAILABLE_POST_TYPE) && !creatorId.equals(userId),
                        postId,
                        limit
                )
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
                                postEntity.creatorId().equals(userId) || userSubscriptionService.isUserSubscribedBySubscriptionIds(
                                        userId,
                                        postSubscriptionService.getSubscriptionsByPostId(
                                                postEntity.id()
                                        ).stream().map(Subscription::id).toList()
                                ),
                                postLikeService.isLikedPost(new LikeRequest(userId, postEntity.id())),
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
                                postEntity.creatorId().equals(userId) || userSubscriptionService.isUserSubscribedBySubscriptionIds(
                                        userId,
                                        postSubscriptionService.getSubscriptionsByPostId(
                                                postEntity.id()
                                        ).stream().map(Subscription::id).toList()
                                ),
                                postLikeService.isLikedPost(new LikeRequest(userId, postEntity.id())),
                                postEntity.isEdited()
                        )
                ).toList();
    }

    @Override
    public Post getPostByUserAndPostIds(String userId, Long postId) {
        PostEntity postEntity = postDao.getPostById(postId).orElseThrow(PostNotFoundException::new);
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
                postEntity.creatorId().equals(userId) || userSubscriptionService.isUserSubscribedBySubscriptionIds(
                        userId,
                        requiredSubscriptions.stream().map(Subscription::id).toList()
                ),
                postLikeService.isLikedPost(new LikeRequest(userId, postEntity.id())),
                postEntity.isEdited()
        );
    }

    @Override
    public void insertPost(PostRequest postRequest) {
        Long postId = postDao.insertPost(postRequest);
        postImageService.insertImagesByPostId(postRequest.imageUrls(), postId);
        postTagService.insertTagsByPostId(postRequest.tagIds(), postId);
        postSubscriptionService.insertSubscriptionsByPostId(postRequest.requiredSubscriptionIds(), postId);
    }

    @Override
    public void updatePost(Long postId, PostRequest postRequest) {
        postDao.updatePost(postId, postRequest);
        postImageService.insertImagesByPostId(postRequest.imageUrls(), postId);
        postTagService.insertTagsByPostId(postRequest.tagIds(), postId);
        postSubscriptionService.insertSubscriptionsByPostId(postRequest.requiredSubscriptionIds(), postId);
    }

    @Override
    public void deletePostById(Long postId) {
        postDao.deletePostById(postId);
    }

    @Override
    public void insertLikeToPost(LikeRequest likeRequest) {
        postLikeService.insertLike(likeRequest);
    }

    @Override
    public void deleteLikeFromPost(LikeRequest likeRequest) {
        postLikeService.deleteLike(likeRequest);
    }
}
