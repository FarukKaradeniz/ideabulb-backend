package com.farukkaradeniz.ideabulb.services;

import com.farukkaradeniz.ideabulb.models.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Page<Post> getPostsByUserId(String userId, Pageable pageable);
    Post getPostById(String postId);
    boolean existsById(String postId);
    void removePostById(String postId);
    Post updatePostContent(String postId, String newContent);
    Post savePost(String title, String content, String username);
}
