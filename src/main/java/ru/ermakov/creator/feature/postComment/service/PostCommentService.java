package ru.ermakov.creator.feature.postComment.service;

import ru.ermakov.creator.feature.postComment.model.PostComment;
import ru.ermakov.creator.feature.postComment.model.PostCommentRequest;

import java.util.List;

public interface PostCommentService {
    List<PostComment> getPostCommentPageByPostAndUserIds(
            Long postId,
            String userId,
            Long replyCommentId,
            Long postCommentId,
            Integer limit
    );

    PostComment getPostCommentByCommentAndUserIds(Long postCommentId, String userId);

    Long getPostCommentCountByPostId(Long postId);

    void insertPostComment(PostCommentRequest postCommentRequest);

    void updatePostComment(Long postCommentId, PostCommentRequest postCommentRequest);

    void deletePostCommentById(Long postCommentId);
}