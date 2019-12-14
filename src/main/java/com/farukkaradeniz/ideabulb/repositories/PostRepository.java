package com.farukkaradeniz.ideabulb.repositories;

import com.farukkaradeniz.ideabulb.models.entities.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends PagingAndSortingRepository<Post, String> {
    List<Post> findAllByUserId(String userId, Pageable pageable);
}
