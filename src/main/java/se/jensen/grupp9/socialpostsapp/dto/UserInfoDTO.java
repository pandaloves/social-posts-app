package se.jensen.grupp9.socialpostsapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Data Transfer Object (DTO) representing basic information about a user.
 * <p>
 * Used in API responses where only essential user information is needed,
 * such as user ID, username, display name, and profile image path.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoDTO {

    /**
     * Unique identifier of the user
     */
    private Long id;

    /**
     * Username of the user
     */
    private String username;

    /**
     * Default constructor.
     * Required for JSON deserialization and frameworks.
     */
    public UserInfoDTO() {
    }

    /**
     * Parameterized constructor to create a UserInfoDTO with all fields.
     *
     * @param id       unique identifier of the user
     * @param username username of the user
     */
    public UserInfoDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    /**
     * Gets the user's ID.
     *
     * @return the unique identifier of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id the unique identifier to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username.
     *
     * @return the username of the user
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
}
