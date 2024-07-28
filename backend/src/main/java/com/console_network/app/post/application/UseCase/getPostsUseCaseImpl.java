package com.console_network.app.post.application.UseCase;
import com.console_network.app.post.domain.model.Post;
import com.console_network.app.post.domain.repository.in.getPostsUseCase;
import com.console_network.app.post.domain.repository.out.postRepository;
import java.util.List;

public class getPostsUseCaseImpl implements getPostsUseCase{
    private final postRepository postRepository;

    public getPostsUseCaseImpl(postRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }
}
