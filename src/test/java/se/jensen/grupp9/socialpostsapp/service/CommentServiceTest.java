package se.jensen.grupp9.socialpostsapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jensen.grupp9.socialpostsapp.dto.CommentRequestDTO;
import se.jensen.grupp9.socialpostsapp.dto.CommentResponseDTO;
import se.jensen.grupp9.socialpostsapp.dto.DTOMapper;
import se.jensen.grupp9.socialpostsapp.exception.CommentNotFoundException;
import se.jensen.grupp9.socialpostsapp.exception.PostNotFoundException;
import se.jensen.grupp9.socialpostsapp.model.Comment;
import se.jensen.grupp9.socialpostsapp.model.Post;
import se.jensen.grupp9.socialpostsapp.model.User;
import se.jensen.grupp9.socialpostsapp.repository.CommentRepository;
import se.jensen.grupp9.socialpostsapp.repository.PostRepository;
import se.jensen.grupp9.socialpostsapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CommentService commentService;

    private Comment testComment;
    private Post testPost;
    private User testUser;
    private CommentRequestDTO commentRequestDTO;

    @BeforeEach
    public void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testUser");

        testPost = new Post();
        testPost.setId(1L);
        testPost.setText("test post");
        testPost.setUser(testUser);

        testComment = new Comment();
        testComment.setId(1L);
        testComment.setText("test comment");
        testComment.setPost(testPost);
        testComment.setUser(testUser);

        commentRequestDTO = new CommentRequestDTO("test comment");
    }

    @Test
    void testGetAllCommentsByPostId() {
        //arrange (mock repo returns testComment in list when findPostByIdOrderByCreatedAtAAsc is called)
        when(commentRepository.findByPostIdOrderByCreatedAtAsc(1L))
                .thenReturn(List.of(testComment));

        //act (CommentService getAllCommentsByPostId method)
        List<CommentResponseDTO> results = commentService.getAllCommentsByPostId(1L);

        //assert (not null list, one comment in list, methd ran once)
        assertNotNull(results);
        assertEquals(1, results.size());
        verify(commentRepository, times(1)).findByPostIdOrderByCreatedAtAsc(1L);
    }

    @Test
    void testGetCommentById_success() {
        //arrange (mock repo returns testComment optional when findById is called)
        when(commentRepository.findById(1L)).thenReturn(Optional.of(testComment));

        //act (CommentService getcommentByIdMethod with 1L parameter)
        CommentResponseDTO result = commentService.getCommentById(1L);

        //assert (not null, correct text value, findById called one time)
        assertNotNull(result);
        assertEquals("test comment", result.text());
        verify(commentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCommentById_fail() {
        // arrange (mock repo returns empty optional when findById is called with any value)
        when(commentRepository.findById(anyLong())).thenReturn(Optional.empty());

        //act(CommentService getCommentById), assert(method caled once, CommentNotFound exception thrown)
        assertThrows(CommentNotFoundException.class, () -> commentService.getCommentById(1L));
        verify(commentRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateComment() {
        //arrange (mock post-repo returns testPost when findById is called with 1L,
        // mock comment-repo returns testComment when save is called with any value),
        // mock userrepo returns testPost optional when findById is called with 1L
        when(postRepository.findById(1L)).thenReturn(Optional.of(testPost));
        when(commentRepository.save(any(Comment.class))).thenReturn(testComment);
        when(userRepository.findById(1L)).thenReturn(Optional.of(testPost));

        //act CommentService createCommentMethod with values 1L and commentRequestDTO
        CommentResponseDTO result = commentService.createComment(1L, 1L, commentRequestDTO);

        //assert(not null, correct text value,
        // findById in user repo called 1 time,
        // save called 1 time
        // findById in comment repo called 1 time )
        assertNotNull(result);
        assertEquals("test comment", result.text());
        verify(postRepository, times(1)).findById(1L);
        verify(commentRepository, times(1)).save(any(Comment.class));
        verify(userRepository, times(1)).findById(1L);

    }

    @Test
    void testCreateComment_fail() {
        // arrange(mock post-repo returns empty optional when findById is called with any value,,
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        //act(CommentService createCommment method),
        // assert(findById called 1 time, PostNotFoundException thrown)
        assertThrows(PostNotFoundException.class, () ->
                commentService.createComment(1L, 1L, commentRequestDTO));
        verify(postRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateComment() {
        //arrange (mock repo returns testComment when findById is called with 1L and when save is called with any value)
        when(commentRepository.findById(1L)).thenReturn(Optional.of(testComment));
        when(commentRepository.save(any(Comment.class))).thenReturn(testComment);

        //act (CommentService updateComment method
        CommentResponseDTO result = commentService.updateComment(1L, commentRequestDTO);

        //assert(not null, correct text, findById called1 time)
        assertNotNull(result);
        assertEquals("test comment", result.text());
        verify(commentRepository, times(1)).findById(1L);
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void testDeleteComment() {
        // arrange(mock repo returns testComment optional when findById is called with value 1L)
        when(commentRepository.findById(1L)).thenReturn(Optional.of(testComment));

        //act (CommentService deleteComment method)
        commentService.deleteComment(1L);

        //assert(findById called 1 time, delete called 1 time)
        verify(commentRepository, times(1)).findById(1L);
        verify(commentRepository, times(1)).delete(testComment);
    }
}
