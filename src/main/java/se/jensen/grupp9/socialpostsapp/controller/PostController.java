package se.jensen.grupp9.socialpostsapp.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensen.grupp9.socialpostsapp.dto.PostRequestDTO;
import se.jensen.grupp9.socialpostsapp.dto.PostResponseDTO;
import se.jensen.grupp9.socialpostsapp.service.CommentService;
import se.jensen.grupp9.socialpostsapp.service.PostService;

/**
 * REST controller for handling post-related actions and endpoints.
 * Supports getting posts for the feed, filtering by user for user wall,
 * getting a single post, as well as creating, updating and removing a post.
 */
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    /**
     * Constructor for PostController
     *
     * @param postService Service for post operations
     */
    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
    }

    /**
     * Get a page of posts in ordered by createdAt (newest first)
     * as a ResponseEntity<Page<PostResponseDTO>>
     *
     * @param userId The users ID (for user wall, omit for feed)
     * @param pageable The page parameters
     * @return A ResponseEntity<Page<PostResponseDTO>>
     */
    @GetMapping()
    public ResponseEntity<Page<PostResponseDTO>> getPosts(@RequestParam(required = false) Long userId, Pageable pageable) {
        //feed, all posts
        if (userId==null) {
            Page<PostResponseDTO> posts = postService.getPosts(pageable);
            return ResponseEntity.ok(posts);
        }
        //user wall, user posts
        Page<PostResponseDTO> posts = postService.getPostsByUserId(pageable,userId);
        return ResponseEntity.ok(posts);
    }

    /**
     * Get a singe post by post id
     *
     * @param id The posts ID
     * @return a ResponseEntity<PostResponseDTO>>
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long id) {
        PostResponseDTO post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    /**
     * Add a post
     *
     * @param dto A PostRequestDTO for the post to be created
     * @param userId
     * @return
     */
    @PostMapping()
    public ResponseEntity<PostResponseDTO> addPost(
            @Valid @RequestBody PostRequestDTO dto,
            @RequestParam Long userId) {
        PostResponseDTO newPost =  postService.createPost(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    /**
     * Updating an existing post
     *
     * @param dto The PostRequestDTO with updated values
     * @param id The ID of the post
     * @return a ResponseEntity of PostResponseDTO type
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(
            @Valid @RequestBody PostRequestDTO dto,
            @PathVariable Long id){
        PostResponseDTO updated = postService.updatePost(id,dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Deleting a post
     *
     * @param id The ID of the post
     * @return An NO_CONTENT empty respponse entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<PostResponseDTO> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
