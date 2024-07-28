// Configuración de posts
package com.console_network.app.post.infrastructure.config;

import com.console_network.app.post.application.Service.PostService;
import com.console_network.app.post.domain.repository.in.createPostUseCase;
import com.console_network.app.post.domain.repository.in.deletePostUseCase;
import com.console_network.app.post.domain.repository.in.getPostByUserUseCase;
import com.console_network.app.post.domain.repository.in.getPostsUseCase;
import com.console_network.app.post.domain.repository.out.postRepository;
import com.console_network.app.post.infrastructure.out.postRepositoryImpl;
import com.console_network.app.post.application.UseCase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.console_network.app.user.domain.repository.out.userRepository; // Importa el bean de userRepository

@Configuration
public class PostConfig {


    @Bean
    public postRepository postRepository(userRepository userRepository) {
        return new postRepositoryImpl(userRepository);
    }


    @Bean
    public createPostUseCase createPostUseCase(postRepository postRepository) {
        return new createPostUseCaseImpl(postRepository);
    }

    @Bean
    public deletePostUseCase deletePostUseCase(postRepository postRepository) {
        return new deletePostUseCaseImpl(postRepository);
    }

    @Bean
    public getPostsUseCase getPostsUseCase(postRepository postRepository) {
        return new getPostsUseCaseImpl(postRepository);
    }

    @Bean
    public getPostByUserUseCase getPostByUserUseCase(postRepository postRepository) {
        return new getPostByUserUseCaseImpl(postRepository);
    }

    @Bean
    public PostService postService(
            createPostUseCase createPostUseCase,
            deletePostUseCase deletePostUseCase,
            getPostsUseCase getPostsUseCase,
            getPostByUserUseCase getPostByUserUseCase,
            userRepository userRepository) { // Inyecta el bean de userRepository
        return new PostService(
                createPostUseCase,
                deletePostUseCase,
                getPostByUserUseCase,
                getPostsUseCase,
                userRepository // Aquí se inyecta el bean
        );
    }
}
