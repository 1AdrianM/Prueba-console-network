package com.console_network.app.user.application.UseCase;
import com.console_network.app.user.domain.repository.in.getUserByIdUseCase;
import com.console_network.app.user.domain.repository.out.userRepository;
import com.console_network.app.user.infrastructure.dto.UserDto;

import java.util.Optional;

public class userGetByIdUseCaseImpl implements getUserByIdUseCase{
    private final userRepository userRepository;

    public userGetByIdUseCaseImpl(com.console_network.app.user.domain.repository.out.userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserDto> getUser(String name) {
        return userRepository.findUserByName(name);
    }
}
