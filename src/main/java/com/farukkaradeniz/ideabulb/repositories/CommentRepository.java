package com.farukkaradeniz.ideabulb.repositories;

import com.farukkaradeniz.ideabulb.models.entities.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommentRepository extends PagingAndSortingRepository<Comment, String> {
    List<Comment> findAllByPostId(String postId, Pageable pageable);
    List<Comment> findAllByUserId(String userId, Pageable pageable);
    void deleteAllByPostId(String postId);
}
