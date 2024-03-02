package ru.ermakov.creator.feature.postComment.service;

import ru.ermakov.creator.feature.postComment.model.Comment;
import ru.ermakov.creator.feature.postComment.model.CommentRequest;
import ru.ermakov.creator.feature.postComment.model.PostCommentEntity;

import java.util.List;

public interface PostCommentService {
    List<Comment> getCommentPageByPostId(Long postId, Long replyCommentId, Long commentId, Integer limit);

    Comment getCommentById(Long commentId);

    Long getCommentCountByPostId(Long postId);

    void insertComment(CommentRequest commentRequest);

    void updateComment(Long commentId, CommentRequest commentRequest);

    void deleteCommentById(Long commentId);
}