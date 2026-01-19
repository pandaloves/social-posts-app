package se.jensen.grupp9.socialpostsapp.dto;

import se.jensen.grupp9.socialpostsapp.model.Comment;
import se.jensen.grupp9.socialpostsapp.model.Post;
import se.jensen.grupp9.socialpostsapp.model.User;


/**
 * Mapper class for converting between entity models and DTOs.
 * <p>
 * Provides static methods to convert Post, Comment, User, and Friendship
 * entities into their corresponding Data Transfer Objects (DTOs) for API responses.
 */
public class DTOMapper {
    /**
     * Converts a User entity to a lightweight UserInfoDTO.
     *
     * @param user the User entity to convert
     * @return the corresponding UserInfoDTO, or null if input is null
     */
    public static UserInfoDTO toUserInfoDTO(User user) {
        if (user == null) return null;

        UserInfoDTO dto = new UserInfoDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        return dto;
    }

    /**
     * Converts a User entity to a full UserDTO with all details.
     *
     * @param user the User entity to convert
     * @return the corresponding UserDTO, or null if input is null
     */
    public static UserDTO toUserDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setBio(user.getBio());
        return dto;
    }

    /**
     *  Converts a Post entity to a PostResponseDTO
     */
    public static PostResponseDTO toPostResponseDTO(Post post) {
        if (post == null) return null;

        PostResponseDTO dto = new PostResponseDTO(
                post.getId(),
                post.getText(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                toUserDTO(post.getUser()),
                null//comments
        );
        return dto;
    }

    /**
     *  Converts a CommentRequestDTO to Comment entity
     */
   public static Comment toComment(CommentRequestDTO dto) {
       if (dto == null) return null;
       return new Comment(dto.text());
   }

    /**
     * Converts a Comment entity to CommentResponseDTO
     */
    public static CommentResponseDTO toCommentResponseDTO(Comment comment) {
        if (comment == null) return null;

        return new CommentResponseDTO(
                comment.getId(),
                comment.getText(),
                comment.getCreatedAt(),
                toUserDTO(comment.getUser())
        );
    }
}
