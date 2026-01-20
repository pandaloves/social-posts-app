package se.jensen.grupp9.socialpostsapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a post in the social app.
 * <p>
 * Each post contains text content written by a user and has a creation timestamp
 * Posts can have multiple comments and can be updated or deleted by the owner.
 * </p>
 *
 * @see User
 * @see Comment
 */

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {

    /**
     * Unique identifier for the post
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The text content of the post
     */
    @Column(nullable = false)
    private String text;

    /**
     * The user who created this post
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * When this post was created (automatically set)
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * When this post was last updated (automatically set)
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * List of comments on this post
     */
    @OneToMany(mappedBy = "post",
    cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    /**
     * Constructor for creating a new post with text.
     *
     * @param text The post text
     */
    public Post(String text){
        this.text = text;
    }
}
