package com.console_network.app.post.applicationTest.UseCasesTest;

import com.console_network.app.post.application.UseCase.getPostByUserUseCaseImpl;
import com.console_network.app.post.domain.model.Post;
import com.console_network.app.post.domain.repository.out.postRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class getPostByUserUseCaseImplTest {

    private postRepository mockPostRepository;
    private getPostByUserUseCaseImpl getPostByUserUseCase;

    @BeforeEach
    public void setUp() {
        mockPostRepository = Mockito.mock(postRepository.class);
        getPostByUserUseCase = new getPostByUserUseCaseImpl(mockPostRepository);
    }

    @Test
    public void testGetPostByUser() {
        String userName = "Alice";
        Post post1 = new Post(UUID.randomUUID(), userName, "Post content 1", LocalTime.now());
        Post post2 = new Post(UUID.randomUUID(), userName, "Post content 2", LocalTime.now());

        when(mockPostRepository.findByUser(userName)).thenReturn(Arrays.asList(post1, post2));

        List<Post> posts = getPostByUserUseCase.getPostByUser(userName);

        assertEquals(2, posts.size());
        assertEquals(post1.getText(), posts.get(0).getText());
        assertEquals(post2.getText(), posts.get(1).getText());

        verify(mockPostRepository, times(1)).findByUser(userName);
    }
}

