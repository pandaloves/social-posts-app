package se.jensen.grupp9.socialpostsapp.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensen.grupp9.socialpostsapp.dto.CommentRequestDTO;
import se.jensen.grupp9.socialpostsapp.dto.CommentResponseDTO;
import se.jensen.grupp9.socialpostsapp.service.CommentService;
import se.jensen.grupp9.socialpostsapp.service.PostService;

import java.util.List;

/**
 * REST controller for handling comment-related actions and endpoints.
 * Supports getting a single comment, updating a comment and removing a comment.
 */
@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    /**
     * Constructor for CommentController
     *
     * @param commentService Service  for comment operations
     */
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Get a single comment from the comment ID
     * @param id The ID of the comment
     * @return A ResponseEntity of CommentResponseDTO type
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> getCommentById(@PathVariable Long id){
        return ResponseEntity.ok(commentService.getCommentById(id));
    }


    /**
     * Updating a comment
     *
     * @param dto The requestDTO with the updated values for the comment
     * @param id The comment ID
     * @return ResponseEntity containing the CommentResponseDTO of the updated comment
     */
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> updateComment(
            @RequestBody CommentRequestDTO dto,
            @PathVariable Long id
    ){
        CommentResponseDTO updatedComment = commentService.updateComment(id, dto);
        return ResponseEntity.ok(updatedComment);
    }

    /**
     * Deleting a comment
     *
     * @param id The id of the comment
     * @return No content ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CommentResponseDTO> deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

}
