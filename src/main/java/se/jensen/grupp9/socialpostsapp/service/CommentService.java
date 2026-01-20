package se.jensen.grupp9.socialpostsapp.service;


import org.springframework.stereotype.Service;
import se.jensen.grupp9.socialpostsapp.dto.CommentRequestDTO;
import se.jensen.grupp9.socialpostsapp.dto.CommentResponseDTO;
import se.jensen.grupp9.socialpostsapp.dto.DTOMapper;
import se.jensen.grupp9.socialpostsapp.exception.CommentNotFoundException;
import se.jensen.grupp9.socialpostsapp.exception.PostNotFoundException;
import se.jensen.grupp9.socialpostsapp.model.Comment;
import se.jensen.grupp9.socialpostsapp.model.Post;
import se.jensen.grupp9.socialpostsapp.repository.CommentRepository;
import se.jensen.grupp9.socialpostsapp.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Service for Comment entity operations
 * <p>
 *     Handles business logic for creating, reading, updating and deleting comments.
 * </p>
 */
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    /**
     * Get all comments for a specific post
     *
     * @param postId The  ID of the post
     * @return List of CommentRepsonseDTOs
     */
    public List<CommentResponseDTO> getAllCommentsByPostId(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId)
                .stream()
                .map(DTOMapper::toCommentResponseDTO)
                .toList();
    }

    /**
     * Get a comment by ID
     *
     * @param id The comments id
     * @return CommentRepsonseDTO
     */
    public CommentResponseDTO getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new CommentNotFoundException("Comment not found with id:" + id));
        return DTOMapper.toCommentResponseDTO(comment);
    }

    /**
     * Create a new comment
     *
     * @param postId The id of the post the comment will belong to
     * @param dto The CommentRequestDTO
     * @return Created CommentResponseDTO
     */
    public CommentResponseDTO createComment(Long postId, CommentRequestDTO dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new PostNotFoundException("Post not found with id:" + postId));
        Comment comment = DTOMapper.toComment(dto);
        comment.setPost(post);
        commentRepository.save(comment);
        return DTOMapper.toCommentResponseDTO(comment);
    }

    /**
     * Update a comment
     *
     * @param id The comment id
     * @param dto The CommentRequestDTO with updated values
     * @return Updated CommentResponseDTO
     */
    public CommentResponseDTO updateComment(Long id, CommentRequestDTO dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new CommentNotFoundException("Comment not found with id:" + id));
        comment.setText(dto.text());
        Comment updatedComment = commentRepository.save(comment);
        return DTOMapper.toCommentResponseDTO(updatedComment);
    }

    /**
     * Delete a comment
     *
     * @param id The comments id
     */
    public void deleteComment(Long id) {
        Comment comment= commentRepository.findById(id)
                .orElseThrow(()-> new CommentNotFoundException("Comment not found with id:" + id));
        commentRepository.delete(comment);
    }
}
