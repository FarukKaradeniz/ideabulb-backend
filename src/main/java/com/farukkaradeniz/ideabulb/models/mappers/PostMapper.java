package com.farukkaradeniz.ideabulb.models.mappers;

import com.farukkaradeniz.ideabulb.models.dtos.PostDTO;
import com.farukkaradeniz.ideabulb.models.entities.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PostMapper {
    private static Logger log = LoggerFactory.getLogger(PostMapper.class);

    public static PostDTO convertToPostDTO(Post post) {
        PostDTO dto = new PostDTO(post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreateDate(),
                post.getUser().getId().toString(),
                post.getComments().stream().map(CommentMapper::convertToCommentDTO).collect(Collectors.toList()));

        log.info("Post Entity to PostDTO Conversion successfully completed.");
        return dto;
    }
}
