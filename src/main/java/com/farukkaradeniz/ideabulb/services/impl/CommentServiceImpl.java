package com.farukkaradeniz.ideabulb.services.impl;

import com.farukkaradeniz.ideabulb.models.dtos.CommentDTO;
import com.farukkaradeniz.ideabulb.models.entities.Comment;
import com.farukkaradeniz.ideabulb.models.entities.Post;
import com.farukkaradeniz.ideabulb.models.entities.User;
import com.farukkaradeniz.ideabulb.models.mappers.CommentMapper;
import com.farukkaradeniz.ideabulb.repositories.CommentRepository;
import com.farukkaradeniz.ideabulb.repositories.PostRepository;
import com.farukkaradeniz.ideabulb.repositories.UserRepository;
import com.farukkaradeniz.ideabulb.services.CommentService;
import com.farukkaradeniz.ideabulb.utils.generators.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * Returns list of comments by given user id
     * @param userId user's id
     * @param page page
     * @param pageSize page size
     * @return list of comments
     */
    @Override
    public List<CommentDTO> getCommentsByUserId(String userId, Integer page, Integer pageSize) {
        PageRequest request = PageRequest.of(page, pageSize);

        return commentRepository
                .findAllByUserId(userId, request)
                .stream()
                .map(CommentMapper::convertToCommentDTO)
                .collect(Collectors.toList());
    }

    /**
     * Returns list of comments by given post id
     * @param postId post's id
     * @param page page
     * @param pageSize page size
     * @return list of comments
     */
    @Override
    public List<CommentDTO> getCommentsByPostId(String postId, Integer page, Integer pageSize) {
        PageRequest request = PageRequest.of(page, pageSize);

        return commentRepository
                .findAllByPostId(postId, request)
                .stream()
                .map(CommentMapper::convertToCommentDTO)
                .collect(Collectors.toList());
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
