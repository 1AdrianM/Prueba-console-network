package com.console_network.app.user.application.UseCase;
import com.console_network.app.user.domain.repository.in.userFollowUseCase;
import com.console_network.app.user.domain.repository.out.userRepository;

public class userAddFollowUseCaseImpl implements userFollowUseCase {
    private final userRepository userRepository;

    public userAddFollowUseCaseImpl(com.console_network.app.user.domain.repository.out.userRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public String Follow(String userOrigen, String userDestino) {
return userRepository.addFollowUser(userOrigen, userDestino);
    }

}
