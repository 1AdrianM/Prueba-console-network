package com.console_network.app.post.applicationTest.UseCasesTest;

import com.console_network.app.post.domain.model.Post;
import com.console_network.app.post.domain.repository.out.postRepository;
import com.console_network.app.post.application.UseCase.getPostsUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class getPostsUseCaseImplTest {

    @Mock
    private postRepository postRepository;

    @InjectMocks
    private getPostsUseCaseImpl getPostsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPost_Success() {
        // Preparar datos de prueba
        Post post1 = new Post();
        post1.setText("Test post 1");
        Post post2 = new Post();
        post2.setText("Test post 2");

        List<Post> mockPostList = Arrays.asList(post1, post2);

        // Configurar el comportamiento del mock
        when(postRepository.findAll()).thenReturn(mockPostList);

        // Llamar al método a probar
        List<Post> result = getPostsUseCase.getAllPost();

        // Verificar el resultado
        assertEquals(2, result.size());
        assertEquals("Test post 1", result.get(0).getText());
        assertEquals("Test post 2", result.get(1).getText());

        // Verificar que el método del mock se llamó correctamente
        verify(postRepository, times(1)).findAll();
    }
}
