package com.console_network.app.post.application.UseCase;
import com.console_network.app.post.domain.model.Post;
import com.console_network.app.post.domain.repository.in.createPostUseCase;
import com.console_network.app.post.domain.repository.out.postRepository;

import java.util.ArrayList;
import java.util.List;

public class createPostUseCaseImpl implements createPostUseCase {
    private final postRepository postRepository;

    public createPostUseCaseImpl(com.console_network.app.post.domain.repository.out.postRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> createPost(String postOwner, Post post) {
        List<Post> postList= new ArrayList<>();
        postList.add(post);
        postRepository.savePost(postOwner, post);
        return postList;

    }

}