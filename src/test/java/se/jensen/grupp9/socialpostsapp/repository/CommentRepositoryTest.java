package se.jensen.grupp9.socialpostsapp.repository;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import se.jensen.grupp9.socialpostsapp.model.Comment;
import se.jensen.grupp9.socialpostsapp.model.Post;
import se.jensen.grupp9.socialpostsapp.model.User;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for CommentRepository using H2 in-memory database
 */
//@DataJpaTest
@Disabled("todo: fix lombok issues")
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Comment testComment;
    private Post testPost;
    private User testUser;

    @BeforeEach
    public void setUp() {
        // create test user
        testUser = new User();
        testUser.setUsername("TestUser");
        testUser.setEmail("test@test.com");

        testUser.setPassword("hashedPassword");
        testUser.setBio("Test bio");

        testEntityManager.persistAndFlush(testUser);

        // Create test post
        testPost = new Post("Test post");
        testPost.setUser(testUser);

        testEntityManager.persistAndFlush(testPost);

        // create test comment
        testComment = new Comment("Test comment");
        testComment.setPost(testPost);
        testComment.setUser(testUser);

        testEntityManager.persistAndFlush(testComment);
    }

    @Test
    public void testFindByCreatedAtAsc() {
        List<Comment> comments = commentRepository.findByPostIdOrderByCreatedAtAsc(testPost.getId());

        // assert: 1 comment in list, with correct text content
        assertNotNull(comments);
        assertFalse(comments.isEmpty());
        assertEquals(1, comments.size());
        assertEquals("Test comment", comments.get(0).getText());

    }

    @Test
    public void testFindByPostId() {
        List<Comment> comments = commentRepository.findByPostId(testPost.getId());

        //assert: 1 comment in list
        assertNotNull(comments);
        assertEquals(1, comments.size());
    }

    @Test
    public void testSaveComment() {
        Comment newComment = new Comment("New comment");
        newComment.setPost(testPost);
        newComment.setUser(testUser);

        Comment saved = commentRepository.save(newComment);
        assertNotNull(saved.getId());
        assertEquals("New comment", saved.getText());
    }

    @Test
    public void testDeleteComment() {
        commentRepository.delete(testComment);
        List<Comment> comments = commentRepository.findAll();
        assertEquals(0, comments.size());
    }

}
