package se.jensen.grupp9.socialpostsapp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for creating and updating posts.
 *
 * @param text The post content (3-200 characters)
 */
public record PostRequestDTO(
        @NotBlank(message = "Post text cannot be empty")
        @Size(min = 3, max = 200, message = "Post text must be between 3 and 200 characters")
        String text
) {}
