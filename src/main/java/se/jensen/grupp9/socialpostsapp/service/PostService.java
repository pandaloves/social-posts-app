package se.jensen.grupp9.socialpostsapp.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se.jensen.grupp9.socialpostsapp.dto.DTOMapper;
import se.jensen.grupp9.socialpostsapp.dto.PostRequestDTO;
import se.jensen.grupp9.socialpostsapp.dto.PostResponseDTO;
import se.jensen.grupp9.socialpostsapp.exception.PostNotFoundException;
import se.jensen.grupp9.socialpostsapp.model.Post;
import se.jensen.grupp9.socialpostsapp.model.User;
import se.jensen.grupp9.socialpostsapp.repository.PostRepository;
import se.jensen.grupp9.socialpostsapp.repository.UserRepository;

/**
 * Service for Post entity operations.
 * <p>
 *     Handles business logic for creating, reading, updating nd deleting posts.
 *     Allows for pagination and filter by user.
 * </p>
 */
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /**
     * Get all posts with pagination.
     *
     * @param pageable Pagination parameters
     * @return Page of PostResponseDTO
     */
    public Page<PostResponseDTO> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(DTOMapper::toPostResponseDTO);
    }

    /**
     * Get all posts with a specific user with pagination
     *
     * @param userId User ID
     * @param pageable Pagination parameters
     * @return Page of PostResponseDTOs with that user
     */
    public Page<PostResponseDTO> getPostsByUserId(Pageable pageable, Long userId) {
        return postRepository.findByUserId(userId, pageable)
                .map(DTOMapper::toPostResponseDTO);
    }

    /**
     * Create a post
     *
     * @param userId The posts user
     * @param dto The PostRequestDTO
     * @return Created PostResponseDTO
     */
    public PostResponseDTO createPost(Long userId, PostRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("User not found"));

        Post post = new Post(dto.text());
        post.setUser(user);
        Post savedPost = postRepository.save(post);

        return DTOMapper.toPostResponseDTO(savedPost);
    }

    /**
     * Update an existing post
     *
     * @param id The posts id
     * @param dto The postRequestDTO with updated values
     * @return Updated PostResponseDTO
     */
    public PostResponseDTO updatePost(Long id, PostRequestDTO dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new PostNotFoundException("Post not found with id:" + id));
        post.setText(dto.text());
        Post updatedPost = postRepository.save(post);
        return DTOMapper.toPostResponseDTO(updatedPost);
    }

    /**
     * Delete a post
     *
     * @param id The Posts ID
     */
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new PostNotFoundException("Post not found with id:" + id));
        postRepository.delete(post);
    }
}
