package com.console_network.app.user.application.UseCase;
import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.domain.repository.in.createUserUseCase;
import com.console_network.app.user.domain.repository.out.userRepository;
import com.console_network.app.user.infrastructure.out.dto.UserDto;

public class userCreateUseCaseImpl implements createUserUseCase{
    private final userRepository userRepository;

    public userCreateUseCaseImpl(com.console_network.app.user.domain.repository.out.userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(User user) {
 return  userRepository.saveUser(user);
    }
}
