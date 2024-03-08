package ru.ermakov.creator.feature.postComment.repository;

import ru.ermakov.creator.feature.postComment.model.PostCommentEntity;
import ru.ermakov.creator.feature.postComment.model.PostCommentRequest;

import java.util.List;
import java.util.Optional;

public interface PostCommentDao {
    List<PostCommentEntity> getPostCommentPageByPostId(
            Long postId,
            Long replyCommentId,
            Long postCommentId,
            Integer limit
    );

    Optional<PostCommentEntity> getPostCommentById(Long commentId);

    Long getPostCommentCountByPostId(Long postId);

    Long insertPostComment(PostCommentRequest postCommentRequest);

    void updatePostComment(Long postCommentId, PostCommentRequest postCommentRequest);

    void deletePostCommentById(Long postCommentId);
}