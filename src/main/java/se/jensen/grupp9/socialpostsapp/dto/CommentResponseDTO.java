package se.jensen.grupp9.socialpostsapp.dto;

import java.time.LocalDateTime;

/**
 * DTO for comment responses (API return values)
 */
public record CommentResponseDTO(
        Long id,
        String text,
        LocalDateTime createdAt,
        UserDTO user
) {}
