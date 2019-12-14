package com.farukkaradeniz.ideabulb.services;

import com.farukkaradeniz.ideabulb.models.dtos.CommentDTO;
import com.farukkaradeniz.ideabulb.models.entities.Comment;

import java.util.List;

public interface CommentService {
    List<CommentDTO> getCommentsByUserId(String userId, Integer page, Integer pageSize);
    List<CommentDTO> getCommentsByPostId(String postId, Integer page, Integer pageSize);
    boolean existById(String commentId);
    void removeCommentById(String commentId);
    void removeCommentsByPostId(String postId);
    Comment updateCommentContent(String commentId, String newContent);
    Comment saveComment(String content, String postId, String username);
}
