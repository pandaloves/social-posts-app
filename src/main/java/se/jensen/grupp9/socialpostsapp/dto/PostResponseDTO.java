package se.jensen.grupp9.socialpostsapp.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for post responses (API return values)
 */
public record PostResponseDTO(
        Long id,
        String text,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UserDTO user,
        List<CommentResponseDTO> comments
) {}
