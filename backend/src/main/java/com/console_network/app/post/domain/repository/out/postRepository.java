package com.console_network.app.post.domain.repository.out;

import com.console_network.app.post.domain.model.Post;
import com.console_network.app.user.domain.model.User;

import java.util.List;

public interface postRepository {
    List<Post> savePost(String postOwner,Post post);
    List<Post> findByUser(String userName);
    List<Post> findAll();
    void deletePost(String id);

}
