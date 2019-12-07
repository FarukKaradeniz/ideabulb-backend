package com.farukkaradeniz.ideabulb.services.impl;

import com.farukkaradeniz.ideabulb.models.entities.Post;
import com.farukkaradeniz.ideabulb.models.entities.User;
import com.farukkaradeniz.ideabulb.repositories.CommentRepository;
import com.farukkaradeniz.ideabulb.repositories.PostRepository;
import com.farukkaradeniz.ideabulb.repositories.UserRepository;
import com.farukkaradeniz.ideabulb.services.PostService;
import com.farukkaradeniz.ideabulb.utils.generators.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private UserRepository userRepository;

    private Logger log = LoggerFactory.getLogger(PostService.class);

    public PostServiceImpl(PostRepository postRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    /**
     * Returns list of posts by given user's id
     * @param userId user id
     * @param pageable paging
     * @return  list of posts
     */
    @Override
    public Page<Post> getPostsByUserId(String userId, Pageable pageable) {
        return postRepository.findAllByUserId(userId, pageable);
    }

    /**
     * Returns the post by given post id
     * @param id
     * @return
     */
    @Override
    public Post getPostById(String id) {
        Optional<Post> post = postRepository.findById(id);
        log.info("Post ID (" + id + ") is present? " + post.isPresent());
        return post.orElse(null);
    }

    /**
     * Check if a post exist in database
     * @param postId post's id
     * @return post's existence
     */
    @Override
    public boolean existsById(String postId) {
        return postRepository.existsById(postId);
    }

    /**
     * Remove post by given post's id
     * @param postId post id
     */
    @Override
    public void removePostById(String postId) {
        if (!existsById(postId)) {
            log.info("There is no post with given ID.");
            return;
        }

        // First remove post's comments(children) then remove post
        commentRepository.deleteAllByPostId(postId);
        log.info("Removed comments for post: " + postId);
        postRepository.deleteById(postId);
        log.info("Post successfully removed.");
    }

    /**
     * Edit post's content
     * @param postId post's id to be edited
     * @param newContent edited message
     * @return edited post
     */
    @Override
    public Post updatePostContent(String postId, String newContent) {
        Post postById = getPostById(postId);
        if (postById == null) {
            return null;
        }
        postById.setContent(newContent);
        log.info("Post content updated.");
        Post saved = postRepository.save(postById);
        log.info("Post saved to the database.");
        return saved;
    }

    /**
     * Create post
     * @param title Post's title
     * @param content Post's content
     * @param username Post's owner
     * @return post
     */
    @Override
    public Post savePost(String title, String content, String username) {
        User user = userRepository.findByUsername(username);
        Post post = new Post(title, content, new Date(), user, Collections.emptyList());
        post.setId(UUIDGenerator.generateUUIDString());
        Post saved = postRepository.save(post);
        log.info("Post saved to the database successfully.");

        return saved;
    }
}
