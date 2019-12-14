package com.farukkaradeniz.ideabulb.controllers;

import com.farukkaradeniz.ideabulb.models.dtos.CommentDTO;
import com.farukkaradeniz.ideabulb.models.entities.Comment;
import com.farukkaradeniz.ideabulb.models.mappers.CommentMapper;
import com.farukkaradeniz.ideabulb.services.CommentService;
import com.farukkaradeniz.ideabulb.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = Constants.API_V1 + "/comments")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;

    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping
    public ResponseEntity<CommentDTO> createComment(
            @RequestBody Map<String, String> fields
    ){
        Comment comment = commentService.saveComment(fields.get("content"),
                fields.get("postId"),
                getCurrentUsername());

        CommentDTO commentDTO = CommentMapper.convertToCommentDTO(comment);

        return new ResponseEntity<>(commentDTO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(
            @PathVariable("commentId") String commentId,
            @RequestBody Map<String, String> fields
    ) {
        Comment comment = commentService.updateCommentContent(commentId, fields.get("content"));
        CommentDTO commentDTO = CommentMapper.convertToCommentDTO(comment);

        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> removeCommentById(
            @PathVariable("commentId") String commentId
    ) {
        commentService.removeCommentById(commentId);

        boolean exists = commentService.existById(commentId);
        return exists ?
                new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST)
                :
                new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getCommentsById(
            @RequestBody Map<String, Object> fields
    ) {
        List<CommentDTO> comments = Collections.emptyList();

        if(!fields.containsKey("userId") && !fields.containsKey("postId")) {
            return new ResponseEntity<>(comments, HttpStatus.NOT_FOUND);
        }

        Integer page = fields.containsKey("page") ? (Integer) fields.get("page") : 0;
        Integer pageSize = fields.containsKey("pageSize") ? (Integer) fields.get("pageSize") : 10;

        if(fields.containsKey("userId"))
            comments = commentService.getCommentsByUserId(fields.get("userId").toString(), page, pageSize);
        else
            comments = commentService.getCommentsByPostId(fields.get("postId").toString(), page, pageSize);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
