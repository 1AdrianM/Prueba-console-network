package com.console_network.app.user.application.UseCase;
import com.console_network.app.user.domain.repository.in.getUserUseCase;
import com.console_network.app.user.domain.repository.out.userRepository;
import com.console_network.app.user.infrastructure.dto.UserDto;

import java.util.List;

public class userGetUseCaseImpl implements getUserUseCase{
    private final userRepository userRepository;

    public userGetUseCaseImpl(com.console_network.app.user.domain.repository.out.userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAllUsers();
    }
}
