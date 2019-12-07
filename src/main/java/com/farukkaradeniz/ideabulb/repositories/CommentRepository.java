package com.farukkaradeniz.ideabulb.repositories;

import com.farukkaradeniz.ideabulb.models.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, String> {
    Page<Comment> findAllByPostId(String postId, Pageable pageable);
    Page<Comment> findAllByUserId(String userId, Pageable pageable);
    void deleteAllByPostId(String postId);
}
