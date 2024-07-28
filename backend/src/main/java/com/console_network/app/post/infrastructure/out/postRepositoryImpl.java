package com.console_network.app.post.infrastructure.out;

import com.console_network.app.post.domain.model.Post;
import com.console_network.app.post.domain.repository.out.postRepository;
import com.console_network.app.post.infrastructure.out.Db.postInMemory;

import java.util.*;
import java.util.stream.Collectors;
import com.console_network.app.user.domain.repository.out.userRepository;

import com.console_network.app.user.infrastructure.out.dto.UserDto;

public class postRepositoryImpl implements postRepository {
    private final userRepository userRepository;

    public postRepositoryImpl(com.console_network.app.user.domain.repository.out.userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> savePost(String postOwner, Post post) {
        Optional<UserDto> optionalUser = this.userRepository.findUserByName(postOwner);
        if (optionalUser.isPresent()) {
            UserDto user = optionalUser.get();
            List<Post> postList = postInMemory.postInMemory.getOrDefault(user.getName(), new ArrayList<>());
            postList.add(post.clone());
            postInMemory.postInMemory.put(user.getName(), postList);
            return postInMemory.postInMemory.get(user.getName());
        } else {
            throw new RuntimeException("User not found: " + postOwner);
        }
    }
    @Override
    public List<Post> findByUser(String userName) {
        return postInMemory.postInMemory.get(userName);
    }

    @Override
    public List<Post> findAll() {

        return postInMemory.postInMemory.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    @Override
    public void deletePost(String id) {
        postInMemory.postInMemory.remove(id);
    }



}
