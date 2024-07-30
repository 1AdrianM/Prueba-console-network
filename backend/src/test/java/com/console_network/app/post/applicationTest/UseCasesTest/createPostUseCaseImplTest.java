package com.console_network.app.post.applicationTest.UseCasesTest;

import com.console_network.app.post.application.UseCase.createPostUseCaseImpl;
import com.console_network.app.post.domain.model.Post;
import com.console_network.app.post.domain.repository.out.postRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class createPostUseCaseImplTest {

    @Mock
    private postRepository postRepository;

    @InjectMocks
    private createPostUseCaseImpl createPostUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPost_Success() {
        String postOwner = "testUser";
        Post post = new Post();
        post.setText("Test post");

        List<Post> result = createPostUseCase.createPost(postOwner, post);

        assertEquals(1, result.size());
        assertEquals(post, result.get(0));
        verify(postRepository, times(1)).savePost(postOwner, post);
    }
}
