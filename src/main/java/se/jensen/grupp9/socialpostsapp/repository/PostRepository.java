package se.jensen.grupp9.socialpostsapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.jensen.grupp9.socialpostsapp.model.Post;

import java.util.List;

/**
 * Repository for Post entity
 *
 * <p>
 * Provides database operations for posts including pagination and filtering by user.
 * </p>
 *
 * @see Post
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    /**
     * Find all posts ordered by creation date (nwest first)
     *
     * @return List of all posts ordered by createdAt descending
     */
    @Query("SELECT p FROM Post p")
    List<Post> findAllOrderByCreatedAtDesc(Pageable pageable);

    /**
     * Find all posts with pagination.
     *
     * @param pageable Pagination parameters (page, size, sort)
     * @return Page of posts
     */
    @Override
    Page<Post> findAll(Pageable pageable);

    /**
     * Find all posts by a specific user with pagination
     *
     * @param userId   The user ID
     * @param pageable Pagination parameters
     * @return Page of posts for the user
     */
    Page<Post> findByUserId(Long userId, Pageable pageable);
}