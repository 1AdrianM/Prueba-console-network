package com.console_network.app.post.application.UseCase;
import com.console_network.app.post.domain.repository.in.deletePostUseCase;
import com.console_network.app.post.domain.repository.out.postRepository;

public class deletePostUseCaseImpl implements deletePostUseCase{
    private final postRepository postRepository;

    public deletePostUseCaseImpl(com.console_network.app.post.domain.repository.out.postRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void deletePost(String userName) {
     postRepository.deletePost(userName);
    }
}
