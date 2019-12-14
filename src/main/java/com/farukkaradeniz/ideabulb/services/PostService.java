package com.farukkaradeniz.ideabulb.services;

import com.farukkaradeniz.ideabulb.models.dtos.PostDTO;
import com.farukkaradeniz.ideabulb.models.entities.Post;

import java.util.List;

public interface PostService {
    List<PostDTO> getPostsByUserId(String userId, Integer page, Integer pageSize);
    Post getPostById(String postId);
    boolean existsById(String postId);
    void removePostById(String postId);
    Post updatePostContent(String postId, String newContent);
    Post savePost(String title, String content, String username);
}
