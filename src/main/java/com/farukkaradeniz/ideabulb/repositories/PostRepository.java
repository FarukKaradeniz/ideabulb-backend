package com.farukkaradeniz.ideabulb.repositories;

import com.farukkaradeniz.ideabulb.models.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, String> {
    Page<Post> findAllByUserId(String userId, Pageable pageable);
}
