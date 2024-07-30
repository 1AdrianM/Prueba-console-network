package com.console_network.app.post.application.Service;
import com.console_network.app.post.infrastructure.Dto.DashBoardPostResponse;
import  com.console_network.app.user.domain.model.User;
import com.console_network.app.post.domain.model.Post;
import com.console_network.app.post.domain.repository.in.*;
import com.console_network.app.user.domain.repository.out.userRepository;
import com.console_network.app.user.infrastructure.dto.UserDto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostService implements createPostUseCase, getPostByUserUseCase, getPostsUseCase {
    private final createPostUseCase createPostUseCase;
    private final  getPostByUserUseCase getPostByUserUseCase;
    private final getPostsUseCase getPostsUseCase;
    private final userRepository userRepository;

    public PostService(createPostUseCase createPostUseCase, getPostByUserUseCase getPostByUserUseCase, getPostsUseCase getPostsUseCase, userRepository userRepository){
        this.createPostUseCase = createPostUseCase;
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

            foundedUser.getPosts().add(post);
            // Verificar que el post se agrega al usuario correcto
            createPostUseCase.createPost(userDto.getName(), post);
            // Verificar que el post se guarda correctamente
            return foundedUser.getPosts();
        } else {
            throw new RuntimeException("User not found: " + postOwner);
        }
    }

public List<DashBoardPostResponse> getDashBoard(String name){
        User user = userRepository.findUsersByName(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    List<User> followingList = user.getFollowing();
        List<DashBoardPostResponse> dashBoardPosts =  new ArrayList<>();
        for(User users: followingList){
        for(Post post: users.getPosts()){
            String formattedMessage = String.format("%s @%s @%s", post.getText(),post.getPostOwner(), post.getCreatedAt().format(formatter));
dashBoardPosts.add(new DashBoardPostResponse(formattedMessage));
        }
        }
        return dashBoardPosts;

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