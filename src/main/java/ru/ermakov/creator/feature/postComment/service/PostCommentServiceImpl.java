package ru.ermakov.creator.feature.postComment.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.postComment.exception.CommentNotFoundException;
import ru.ermakov.creator.feature.postComment.model.Comment;
import ru.ermakov.creator.feature.postComment.model.CommentRequest;
import ru.ermakov.creator.feature.postComment.model.PostCommentEntity;
import ru.ermakov.creator.feature.postComment.repository.PostCommentDao;
import ru.ermakov.creator.feature.user.service.UserService;

import java.util.List;

@Service
public class PostCommentServiceImpl implements PostCommentService {
    private final PostCommentDao postCommentDao;
    private final UserService userService;

    public PostCommentServiceImpl(PostCommentDao postCommentDao, UserService userService) {
        this.postCommentDao = postCommentDao;
        this.userService = userService;
    }

    @Override
    public List<Comment> getCommentPageByPostId(Long postId, Long replyCommentId, Long commentId, Integer limit) {
        return postCommentDao.getCommentPageByPostId(
                        postId,
                        replyCommentId,
                        commentId,
                        limit
                )
                .stream()
                .map(postCommentEntity ->
                        new Comment(
                                postCommentEntity.id(),
                                userService.getUserById(postCommentEntity.userId()),
                                postCommentEntity.postId(),
                                postCommentEntity.replyCommentId(),
                                postCommentEntity.content(),
                                postCommentEntity.publicationDate(),
                                postCommentEntity.isEdited()
                        )
                ).toList();
    }

    @Override
    public Comment getCommentById(Long commentId) {
        PostCommentEntity postCommentEntity = postCommentDao.getCommentById(commentId)
                .orElseThrow(CommentNotFoundException::new);
        return new Comment(
                postCommentEntity.id(),
                userService.getUserById(postCommentEntity.userId()),
                postCommentEntity.postId(),
                postCommentEntity.replyCommentId(),
                postCommentEntity.content(),
                postCommentEntity.publicationDate(),
                postCommentEntity.isEdited()
        );
    }

    @Override
    public Long getCommentCountByPostId(Long postId) {
        return postCommentDao.getCommentCountByPostId(postId);
    }

    @Override
    public void insertComment(CommentRequest commentRequest) {
        postCommentDao.insertComment(commentRequest);
    }

    @Override
    public void updateComment(Long commentId, CommentRequest commentRequest) {
        postCommentDao.updateComment(commentId, commentRequest);
    }

    @Override
    public void deleteCommentById(Long commentId) {
        postCommentDao.deleteCommentById(commentId);
    }
}
