package se.jensen.grupp9.socialpostsapp.model;

import jakarta.persistence.*;

/**
 * Represents a user in the social app.
 * <p>
 * Each user has a unique username and email, a role,
 * display name, bio, profile image path, and can have multiple posts and comments.
 */
@Entity
@Table(name = "app_user")
public class User {

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Unique username of the user.
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * Unique email of the user.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * Password of the user (should be stored hashed).
     */
    @Column(nullable = false)
    private String password;


    /**
     * Short bio of the user.
     */
    @Column(nullable = false)
    private String bio;

    /**
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The bio of the user.
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio The bio to set.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }
}
