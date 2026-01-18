package se.jensen.grupp9.socialpostsapp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for creating an updating comments.
 *
 * @param text The comment content content (1-200 characters)
 */
public record CommentRequestDTO(
        @NotBlank(message = "Comment text cannot be empty")
        @Size(min =  1, max = 200, message = "Comment text must be between 1 and 200 characters")
        String text
) {}
