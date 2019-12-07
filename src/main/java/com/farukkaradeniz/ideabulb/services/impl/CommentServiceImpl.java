package com.farukkaradeniz.ideabulb.services.impl;

import com.farukkaradeniz.ideabulb.models.entities.Comment;
import com.farukkaradeniz.ideabulb.models.entities.Post;
import com.farukkaradeniz.ideabulb.models.entities.User;
import com.farukkaradeniz.ideabulb.repositories.CommentRepository;
import com.farukkaradeniz.ideabulb.repositories.PostRepository;
import com.farukkaradeniz.ideabulb.repositories.UserRepository;
import com.farukkaradeniz.ideabulb.services.CommentService;
import com.farukkaradeniz.ideabulb.utils.generators.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private Logger log = LoggerFactory.getLogger(CommentService.class);

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * Returns list of comments made by user
     * @param userId user's id
     * @param pageable paging
     * @return list of comments
     */
    @Override
    public Page<Comment> getCommentsByUserId(String userId, Pageable pageable) {
        return commentRepository.findAllByUserId(userId, pageable);
    }

    /**
     * Returns list of comments made to the post
     * @param postId post's id
     * @param pageable paging
     * @return list of comments
     */
    @Override
    public Page<Comment> getCommentsByPostId(String postId, Pageable pageable) {
        return commentRepository.findAllByPostId(postId, pageable);
    }

    /**
     * Check if comment exists in databse
     * @param commentId comment's id
     * @return comment's existence
     */
    @Override
    public boolean existById(String commentId) {
        return commentRepository.existsById(commentId);
    }

    /**
     * Remove comment
     * @param commentId comment's id
     */
    @Override
    public void removeCommentById(String commentId) {
        if (existById(commentId)) {
            commentRepository.deleteById(commentId);
            log.info("Comment deleted successfully.");
            return;
        }
        log.info("There is no comment with given ID.");
    }

    /**
     * Remove comments made to the post
     * @param postId post's id
     */
    @Override
    public void removeCommentsByPostId(String postId) {
        if (postRepository.existsById(postId)) {
            commentRepository.deleteAllByPostId(postId);
            log.info("Comments deleted successfully.");
            return;
        }
        log.info("There is no post with given ID.");
    }

    /**
     * Edit comment's content
     * @param commentId comment's id
     * @param newContent edited content
     * @return edited comment
     */
    @Override
    public Comment updateCommentContent(String commentId, String newContent) {
        Optional<Comment> byId = commentRepository.findById(commentId);
        if (byId.isEmpty()) {
            log.error("No comment found with given ID.");
            return null;
        }
        Comment comment = byId.get();
        comment.setContent(newContent);
        log.info("Comment content updated.");
        Comment saved = commentRepository.save(comment);
        log.info("Comment saved to the database");
        return saved;
    }

    /**
     * Create comment
     * @param content comment's content
     * @param postId post's id
     * @param username comment's owner
     * @return created comment details
     */
    @Override
    public Comment saveComment(String content, String postId, String username) {
        Optional<Post> postById = postRepository.findById(postId);
        if (postById.isEmpty()) {
            log.error("There is no post for comments to save.");
            return null;
        }
        User user = userRepository.findByUsername(username);
        Comment comment = new Comment(content, new Date(), user, postById.get());
        comment.setId(UUIDGenerator.generateUUIDString());
        Comment saved = commentRepository.save(comment);
        log.info("Comment saved to the database successfully.");

        return saved;
    }
}
