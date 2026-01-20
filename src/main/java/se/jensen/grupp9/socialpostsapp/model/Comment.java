package se.jensen.grupp9.socialpostsapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Represents a comment in the social app
 *
 * <p>
 *     A comment is created by a user on a post. Each comment has text content
 *     and a creation timestamp. Users can comment on posts.
 * </p>
 *
 * @see Post
 * @see User
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {

    /**
     * Unique identifier for the comment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The text content of the comment
     */
    @Column(nullable = false)
    private String text;

    /**
     * When this comment was created
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * The post this comment belongs to
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    /**
     * The user who wrote  this comment
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Contructor for creating a new comment with text
     *
     * @param text The comment text
     */
    public Comment(String text) {
        this.text = text;
    }
}
