package com.console_network.app.post.infrastructureTest.repositoryImplTest;

import com.console_network.app.post.domain.model.Post;
import com.console_network.app.post.infrastructure.InMemoryDb.postInMemory;
import com.console_network.app.post.infrastructure.repositoryImpl.postRepositoryImpl;
import com.console_network.app.user.domain.repository.out.userRepository;
import com.console_network.app.user.infrastructure.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class postRepositoryImplTest {

    private postRepositoryImpl postRepository;
    private userRepository mockUserRepository;

    @BeforeEach
    public void setUp() {
        mockUserRepository = Mockito.mock(userRepository.class);
        postRepository = new postRepositoryImpl(mockUserRepository);
        postInMemory.postInMemory.clear(); // Limpiar el almacenamiento en memoria antes de cada prueba
    }

    @Test
    public void testSavePost_UserExists() {
        String postOwner = "Alice";
        Post post = new Post(UUID.randomUUID(), postOwner, "Test post content", LocalTime.now());

        UserDto userDto = new UserDto();
        userDto.setName(postOwner);

        when(mockUserRepository.findUserByName(postOwner)).thenReturn(Optional.of(userDto));

        List<Post> savedPosts = postRepository.savePost(postOwner, post);

        assertNotNull(savedPosts);
        assertEquals(1, savedPosts.size());
        assertEquals(post.getText(), savedPosts.get(0).getText());
        assertEquals(post.getPostOwner(), savedPosts.get(0).getPostOwner());
    }

    @Test
    public void testSavePost_UserDoesNotExist() {
        String postOwner = "NonExistentUser";
        Post post = new Post(UUID.randomUUID(), postOwner, "Test post content", LocalTime.now());

        when(mockUserRepository.findUserByName(postOwner)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            postRepository.savePost(postOwner, post);
        });

        assertEquals("User not found: " + postOwner, exception.getMessage());
    }

    @Test
    public void testFindByUser() {
        String userName = "Alice";
        Post post1 = new Post(UUID.randomUUID(), userName, "Post content 1", LocalTime.now());
        Post post2 = new Post(UUID.randomUUID(), userName, "Post content 2", LocalTime.now());

        postInMemory.postInMemory.put(userName, Arrays.asList(post1, post2));

        List<Post> posts = postRepository.findByUser(userName);

        assertNotNull(posts);
        assertEquals(2, posts.size());
        assertEquals(post1.getText(), posts.get(0).getText());
        assertEquals(post2.getText(), posts.get(1).getText());
    }

    @Test
    public void testFindAll() {
        String userName1 = "Alice";
        String userName2 = "Bob";
        Post post1 = new Post(UUID.randomUUID(), userName1, "Post content 1", LocalTime.now());
        Post post2 = new Post(UUID.randomUUID(), userName1, "Post content 2", LocalTime.now());
        Post post3 = new Post(UUID.randomUUID(), userName2, "Post content 3", LocalTime.now());

        postInMemory.postInMemory.put(userName1, Arrays.asList(post1, post2));
        postInMemory.postInMemory.put(userName2, Collections.singletonList(post3));

        List<Post> allPosts = postRepository.findAll();

        assertNotNull(allPosts);
        assertEquals(3, allPosts.size());
        List<String> postContents = Arrays.asList(post1.getText(), post2.getText(), post3.getText());
        assertTrue(allPosts.stream().map(Post::getText).collect(Collectors.toList()).containsAll(postContents));
    }
}
