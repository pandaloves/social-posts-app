package se.jensen.grupp9.socialpostsapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import se.jensen.grupp9.socialpostsapp.dto.PostRequestDTO;
import se.jensen.grupp9.socialpostsapp.dto.PostResponseDTO;
import se.jensen.grupp9.socialpostsapp.exception.PostNotFoundException;
import se.jensen.grupp9.socialpostsapp.model.Post;
import se.jensen.grupp9.socialpostsapp.model.User;
import se.jensen.grupp9.socialpostsapp.repository.PostRepository;
import se.jensen.grupp9.socialpostsapp.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService;

    private Post testPost;
    private User testUser;
    private PostRequestDTO postRequestDTO;

    @BeforeEach
    public void setUp() {

        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testUser");

        testPost = new Post();
        testPost.setId(1L);
        testPost.setText("test post");
        testPost.setUser(testUser);

        postRequestDTO = new PostRequestDTO("test post");
    }

    @Test
    void getTestPost() {
        //arrange
        // (page containing test post,
        // mock repo returns this page when findAll() is called)
        Pageable pageble = PageRequest.of(0, 10);
        Page<Post> testPostPage = new PageImpl<>(List.of(testPost), pageble, 1);

        when(postRepository.findAll(pageble)).thenReturn(testPostPage);

        //act (PostService getPosts method)
        Page<PostResponseDTO> results = postService.getPosts(pageble);

        //assert (one result, findAll method called 1 time)
        assertNotNull(results);
        assertEquals(1, results.getContent().size());
        verify(postRepository, times(1)).findAll(pageble);
    }

    @Test
    void testGetPostById_success() {
        // arrange (mock repo returns testPost-optional when findById is called)
        when(postRepository.findById(1L)).thenReturn(Optional.of(testPost));

        //act (PostService getPostByID method with 1L as ID)
        PostResponseDTO results = postService.getPostById(1L);

        //assert (correct post text, findByID called 1 time)
        assertNotNull(results);
        assertEquals("test post", results.text());
        verify(postRepository, times(1)).findById(1L);
    }

    @Test
    void testGetPostById_fail() {
        //arrange (mock repo returns empty optinal when findById is called)
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        // act(PostService getPostById method),
        // assert(throws PostNotFoundException, findById called 1 time)
        assertThrows(PostNotFoundException.class, () -> postService.getPostById(1L));
        verify(postRepository, times(1)).findById(1L);
    }

    @Test
    void testCreatePost() {
        // arrange(mock user repo returns testUser in optional when findById is called,
        // mock post repo returns testPost in optional when save is called)
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        when(postRepository.save(any(Post.class))).thenReturn(testPost);

        // act (PostService createPostMethod)
        PostResponseDTO result = postService.createPost(1L, postRequestDTO);

        //assert (correct post text, findById called 1 time, save called 1 time)
        assertNotNull(result);
        assertEquals("test post", result.text());
        verify(userRepository, times(1)).findById(1L);
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    void testDeletePost() {
        //arrange (mock repo returns testPost optional when findById is called)
        when(postRepository.findById(1L)).thenReturn(Optional.of(testPost));

        //act (PostService deletePost method)
        postService.deletePost(1L);

        //assert (findById called 1 time, delete called 1 time)
        verify(postRepository, times(1)).findById(1L);
        verify(postRepository, times(1)).delete(testPost);
    }

}