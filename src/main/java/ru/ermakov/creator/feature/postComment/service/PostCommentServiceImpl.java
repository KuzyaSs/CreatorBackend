package ru.ermakov.creator.feature.postComment.service;

import org.springframework.stereotype.Service;
import ru.ermakov.creator.feature.postComment.exception.CommentNotFoundException;
import ru.ermakov.creator.feature.postComment.model.PostComment;
import ru.ermakov.creator.feature.postComment.model.PostCommentRequest;
import ru.ermakov.creator.feature.postComment.model.PostCommentEntity;
import ru.ermakov.creator.feature.postComment.repository.PostCommentDao;
import ru.ermakov.creator.feature.postCommentLike.model.PostCommentLikeRequest;
import ru.ermakov.creator.feature.postCommentLike.service.PostCommentLikeService;
import ru.ermakov.creator.feature.user.service.UserService;

import java.util.List;

@Service
public class PostCommentServiceImpl implements PostCommentService {
    private final PostCommentDao postCommentDao;
    private final UserService userService;
    private final PostCommentLikeService postCommentLikeService;

    public PostCommentServiceImpl(PostCommentDao postCommentDao, UserService userService, PostCommentLikeService postCommentLikeService) {
        this.postCommentDao = postCommentDao;
        this.userService = userService;
        this.postCommentLikeService = postCommentLikeService;
    }

    @Override
    public List<PostComment> getPostCommentPageByPostAndUserIds(
            Long postId,
            String userId,
            Long replyCommentId,
            Long postCommentId,
            Integer limit
    ) {
        return postCommentDao.getPostCommentPageByPostId(
                        postId,
                        replyCommentId,
                        postCommentId,
                        limit
                )
                .stream()
                .map(postCommentEntity ->
                        new PostComment(
                                postCommentEntity.id(),
                                userService.getUserById(postCommentEntity.userId()),
                                postCommentEntity.postId(),
                                postCommentEntity.replyCommentId(),
                                postCommentEntity.content(),
                                postCommentEntity.publicationDate(),
                                postCommentLikeService.getPostCommentLikeCountByPostCommentId(postCommentEntity.id()),
                                postCommentLikeService.isLikedPostComment(
                                        new PostCommentLikeRequest(userId, postCommentEntity.id())
                                ),
                                postCommentEntity.isEdited()
                        )
                ).toList();
    }

    @Override
    public PostComment getPostCommentByCommentAndUserIds(Long postCommentId, String userId) {
        PostCommentEntity postCommentEntity = postCommentDao.getPostCommentById(postCommentId)
                .orElseThrow(CommentNotFoundException::new);
        return new PostComment(
                postCommentEntity.id(),
                userService.getUserById(postCommentEntity.userId()),
                postCommentEntity.postId(),
                postCommentEntity.replyCommentId(),
                postCommentEntity.content(),
                postCommentEntity.publicationDate(),
                postCommentLikeService.getPostCommentLikeCountByPostCommentId(postCommentEntity.id()),
                postCommentLikeService.isLikedPostComment(
                        new PostCommentLikeRequest(userId, postCommentEntity.id())
                ),
                postCommentEntity.isEdited()
        );
    }

    @Override
    public Long getPostCommentCountByPostId(Long postId) {
        return postCommentDao.getPostCommentCountByPostId(postId);
    }

    @Override
    public Long insertPostComment(PostCommentRequest postCommentRequest) {
        return postCommentDao.insertPostComment(postCommentRequest);
    }

    @Override
    public void updatePostComment(Long postCommentId, PostCommentRequest postCommentRequest) {
        postCommentDao.updatePostComment(postCommentId, postCommentRequest);
    }

    @Override
    public void deletePostCommentById(Long postCommentId) {
        postCommentDao.deletePostCommentById(postCommentId);
    }
}