package com.console_network.app.post.application.UseCase;
import com.console_network.app.post.domain.model.Post;
import com.console_network.app.post.domain.repository.in.getPostByUserUseCase;
import com.console_network.app.post.domain.repository.out.postRepository;

import java.util.List;

public class getPostByUserUseCaseImpl implements  getPostByUserUseCase{
    private final postRepository postRepository;

    public getPostByUserUseCaseImpl(postRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getPostByUser(String userName) {
        return postRepository.findByUser(userName);
    }
}
