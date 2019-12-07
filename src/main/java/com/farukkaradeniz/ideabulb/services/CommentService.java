package com.farukkaradeniz.ideabulb.services;

import com.farukkaradeniz.ideabulb.models.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Page<Comment> getCommentsByUserId(String userId, Pageable pageable);
    Page<Comment> getCommentsByPostId(String postId, Pageable pageable);
    boolean existById(String commentId);
    void removeCommentById(String commentId);
    void removeCommentsByPostId(String postId);
    Comment updateCommentContent(String commentId, String newContent);
    Comment saveComment(String content, String postId, String username);
}
