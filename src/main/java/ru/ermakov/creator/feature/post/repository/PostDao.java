package ru.ermakov.creator.feature.post.repository;

import ru.ermakov.creator.feature.post.model.PostEntity;
import ru.ermakov.creator.feature.post.model.PostRequest;

import java.util.List;
import java.util.Optional;

public interface PostDao {
    List<PostEntity> getFilteredPostPageByUserId(
            String userId,
            List<String> followedCreatorIds,
            List<Long> selectedCategoryIds,
            Boolean isEverything,
            List<Long> purchasedSubscriptionIds,
            Boolean isAvailable,
            Long postId,
            Integer limit
    );

    List<PostEntity> getFilteredFollowingPostPageByUserId(
            String userId,
            List<String> followedCreatorIds,
            List<Long> selectedCategoryIds,
            Boolean isEverything,
            List<Long> purchasedSubscriptionIds,
            Boolean isOnlyAllowed,
            Long postId,
            Integer limit
    );

    List<PostEntity> getFilteredPostPageByCreatorId(
            String creatorId,
            List<Long> selectedTagIds,
            Boolean isEverything,
            List<Long> purchasedSubscriptionIds,
            Boolean isOnlyAllowed,
            Long postId,
            Integer limit
    );

    List<PostEntity> getPostPageByUserIdAndSearchQuery(String searchQuery, Long postId, Integer limit);

    Optional<PostEntity> getPostById(Long postId);

    Long insertPost(PostRequest postRequest);

    void updatePost(Long postId, PostRequest postRequest);

    void deletePostById(Long postId);
}
