package se.jensen.grupp9.socialpostsapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Data Transfer Object for transferring user information.
 * <p>
 * Contains user identification, username, email, role, display name,
 * biography, and profile image path.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    /**
     * The unique ID of the user.
     */
    private Long id;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The biography or description of the user.
     */
    private String bio;

    /**
     * Default no-args constructor.
     */
    public UserDTO() {
    }

    /**
     * Constructs a new UserDTO with all fields.
     *
     * @param id       the user ID
     * @param username the username
     * @param email    the email address
     * @param bio      the biography
     */
    public UserDTO(Long id, String username, String email, String bio) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.bio = bio;
    }

    /**
     * Returns the user ID.
     *
     * @return the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user ID.
     *
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the email address.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the biography of the user.
     *
     * @return the bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * Sets the biography of the user.
     *
     * @param bio the bio to set
     */
    public void setBio(String bio) {
        this.bio = bio;
    }
}
