package com.console_network.app.post.domain.repository.in;

import com.console_network.app.post.domain.model.Post;

import java.util.List;

public interface createPostUseCase {
    List<Post> createPost(String postOwner, Post post);

}
