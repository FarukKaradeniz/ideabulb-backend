package com.farukkaradeniz.ideabulb.models.mappers;

import com.farukkaradeniz.ideabulb.models.dtos.CommentDTO;
import com.farukkaradeniz.ideabulb.models.entities.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    private static Logger log = LoggerFactory.getLogger(CommentMapper.class);

    public static CommentDTO convertToCommentDTO(Comment comment) {
        CommentDTO dto = new CommentDTO(comment.getId(),
                comment.getContent(),
                comment.getUser().getId().toString(),
                comment.getPost().getId().toString(),
                comment.getCreateDate());

        log.info("Comment Entity to CommentDTO Conversion successfully completed.");
        return dto;
    }
}
