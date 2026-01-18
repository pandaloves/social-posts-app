package se.jensen.grupp9.socialpostsapp.repository;

import org.springframework.stereotype.Repository;
import se.jensen.grupp9.socialpostsapp.model.Comment;

import java.util.List;

/**
 * Repository for Comment entity
 *
 * <p>
 *     Provides database operations for comments including filtering
 *     by post.
 * </p>
 *
 * @see Comment
 */
@Repository
public interface CommentRepository {

    /**
     * Find all comments for a specific post ordered by creation date (oldest first)
     *
     * @param postId The post id
     * @return List of comments ordered by createdAt ascending
     */
    List<Comment> findByPostIdOrderByCreatedAtAsc (Long postId);

    /**
     * Find all comments for a specific post.
     *
     * @param postId The post ID
     * @return List of comments for the post
     */
    List<Comment> findByPostId(Long postId);
}
