package com.console_network.app.post.application.Service;
import com.console_network.app.post.infrastructure.out.Db.postInMemory;
import  com.console_network.app.user.domain.model.User;
import com.console_network.app.post.domain.model.Post;
import com.console_network.app.post.domain.repository.in.*;
import com.console_network.app.user.domain.repository.out.userRepository;
import com.console_network.app.user.infrastructure.out.dto.UserDto;

import java.util.List;
import java.util.Optional;

public class PostService implements createPostUseCase, deletePostUseCase, getPostByUserUseCase, getPostsUseCase {
    private final createPostUseCase createPostUseCase;
    private final deletePostUseCase deletePostUseCase;
    private final  getPostByUserUseCase getPostByUserUseCase;
    private final getPostsUseCase getPostsUseCase;
    private final userRepository userRepository;

    public PostService(createPostUseCase createPostUseCase, deletePostUseCase deletePostUseCase, getPostByUserUseCase getPostByUserUseCase, getPostsUseCase getPostsUseCase, userRepository userRepository){
        this.createPostUseCase = createPostUseCase;
        this.deletePostUseCase = deletePostUseCase;
        this.getPostByUserUseCase = getPostByUserUseCase;
        this.getPostsUseCase = getPostsUseCase;
        this.userRepository = userRepository;
    }


    @Override
    public List<Post> createPost(String postOwner, Post post) {
        Optional<UserDto> user = userRepository.findUserByName(postOwner);
        if (user.isPresent()) {
            UserDto userDto = user.get();
            User foundedUser = userRepository.findUsersByName(userDto.getName());

            // Log para verificar que estamos trabajando con el usuario correcto
            System.out.println("Usuario encontrado: " + foundedUser.getName());

            foundedUser.getPosts().add(post);

            // Verificar que el post se agrega al usuario correcto
            System.out.println("Posts del usuario antes de guardar: " + foundedUser.getPosts());

            createPostUseCase.createPost(userDto.getName(), post);

            // Verificar que el post se guarda correctamente
            System.out.println("Posts del usuario después de guardar: " + foundedUser.getPosts());

            return foundedUser.getPosts();
        } else {
            throw new RuntimeException("User not found: " + postOwner);
        }
    }




    @Override
    public void deletePost(String userName) {
deletePostUseCase.deletePost(userName);
    }

    @Override
    public List<Post> getPostByUser(String userName) {
        return getPostByUserUseCase.getPostByUser(userName);
    }

    @Override
    public List<Post> getAllPost() {
        return getPostsUseCase.getAllPost();
    }
}