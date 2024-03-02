package ru.ermakov.creator.feature.postComment.repository;

import ru.ermakov.creator.feature.postComment.model.PostCommentEntity;
import ru.ermakov.creator.feature.postComment.model.CommentRequest;

import java.util.List;
import java.util.Optional;

public interface PostCommentDao {
    List<PostCommentEntity> getCommentPageByPostId(Long postId, Long replyCommentId, Long commentId, Integer limit);

    Optional<PostCommentEntity> getCommentById(Long commentId);

    Long getCommentCountByPostId(Long postId);

    void insertComment(CommentRequest commentRequest);

    void updateComment(Long commentId, CommentRequest commentRequest);

    void deleteCommentById(Long commentId);
}