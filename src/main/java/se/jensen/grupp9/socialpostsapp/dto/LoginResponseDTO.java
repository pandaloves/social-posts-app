package se.jensen.grupp9.socialpostsapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Data Transfer Object used to return login results to the client.
 * <p>
 * This DTO contains user information and authentication status
 * after a successful or failed login attempt.
 * Null values will be excluded from JSON responses.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponseDTO {

    /**
     * Unique ID of the authenticated user.
     */
    private Long userId;

    /**
     * Username of the authenticated user.
     */
    private String username;

    /**
     * Indicates whether the login attempt was successful.
     */
    private boolean success;

    /**
     * Default constructor.
     */
    public LoginResponseDTO() {
    }

    /**
     * Constructor used to create a complete login response.
     *
     * @param userId   unique user identifier
     * @param username account username
     * @param success  login result status
     */
    public LoginResponseDTO(Long userId, String username, boolean success) {
        this.userId = userId;
        this.username = username;
        this.success = success;
    }

    /**
     * Returns the user ID.
     *
     * @return user ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Returns the username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns whether login was successful.
     *
     * @return true if login succeeded, false otherwise
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the user ID.
     *
     * @param userId unique user identifier
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Sets the username.
     *
     * @param username account username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the login result status.
     *
     * @param success true if login succeeded
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Returns a string representation of the login response.
     *
     * @return formatted login response string
     */
    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", success=" + success +
                '}';
    }
}
