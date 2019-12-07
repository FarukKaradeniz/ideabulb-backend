package com.farukkaradeniz.ideabulb.controllers;

import com.farukkaradeniz.ideabulb.models.dtos.PostDTO;
import com.farukkaradeniz.ideabulb.models.entities.Post;
import com.farukkaradeniz.ideabulb.models.mappers.PostMapper;
import com.farukkaradeniz.ideabulb.services.PostService;
import com.farukkaradeniz.ideabulb.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = Constants.API_V1 + "/posts")
public class PostController extends BaseController {

    @Autowired
    private PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(
            @PathVariable("postId") String postId
    ) {
        Post post = postService.getPostById(postId);
        PostDTO postDTO = PostMapper.convertToPostDTO(post);

        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping
    public ResponseEntity<PostDTO> createPost(
            @RequestBody Map<String, String> fields
            ) {
        Post post = postService.savePost(fields.get("title"),
                fields.get("content"),
                getCurrentUsername());

        PostDTO postDTO = PostMapper.convertToPostDTO(post);

        return new ResponseEntity<>(postDTO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(
            @PathVariable("postId") String postId,
            @RequestBody Map<String, String> fields
    ) {
        Post post = postService.updatePostContent(postId,
                fields.get("content"));

        PostDTO postDTO = PostMapper.convertToPostDTO(post);

        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> removePost(
            @PathVariable("postId") String postId
    ) {
        postService.removePostById(postId);

        boolean exists = postService.existsById(postId);
        return exists ?
                new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST)
                :
                new ResponseEntity<>("success", HttpStatus.OK);
    }

    // TODO create method for returning list of posts with paging
}
