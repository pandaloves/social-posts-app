package se.jensen.grupp9.socialpostsapp.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import se.jensen.grupp9.socialpostsapp.model.Post;
import se.jensen.grupp9.socialpostsapp.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for PostRepository using H2 in-memory database
 */
//@DataJpaTest
@Disabled("todo: fix lombok issues")
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private User testUser;
    private Post testPost;

    @BeforeEach
    public void setUp() {
        // create test user
        testUser = new User();
        testUser.setUsername("testUser");
        testUser.setEmail("test@example.com");

        testUser.setPassword("hashedPassword");
        testUser.setBio("test bio");

        testEntityManager.persistAndFlush(testUser);

        // Create test post
        testPost = new Post("Test post text");
        testPost.setUser(testUser);

        testEntityManager.persistAndFlush(testPost);
    }

    @Test
    public void testFindAllWithPagination() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> posts = postRepository.findAll(pageable);

        // assert: 1 post with content
        assertNotNull(posts);
        assertTrue(posts.hasContent());
        assertEquals(1, posts.getTotalElements());
    }

    @Test
    public void testFindByUserIdWithPagination() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Post> posts = postRepository.findByUserId(testUser.getId(), pageable);

        //assert: 1 post foundby user id, has correct content
        assertNotNull(posts);
        assertTrue(posts.hasContent());
        assertEquals(1, posts.getTotalElements());
        assertEquals("Test post text", posts.getContent().get(0).getText());
    }

    @Test
    public void testFindAllByOrderByCreatedAtDesc() {
        List<Post> posts = postRepository.findAllOrderByCreatedAtDesc();
        //assert: one post in list
        assertNotNull(posts);
        assertFalse(posts.isEmpty());
        assertEquals(1, posts.size());
    }

    @Test
    public void testSavePost() {
        Post newPost = new Post("New post");
        newPost.setUser(testUser);

        Post savedPost = postRepository.save(newPost);

        //assert: saved post has correct content
        assertNotNull(savedPost.getId());
        assertEquals("New post", savedPost.getText());
    }

    @Test
    public void testDeletePost() {
        postRepository.delete(testPost);

        List<Post> posts = postRepository.findAll();
        assertEquals(0, posts.size());
    }
}
