package com.console_network.app.user.application.Service;
import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.domain.repository.in.*;
import com.console_network.app.user.infrastructure.dto.UserDto;

import java.util.List;
import java.util.Optional;

public class UserService implements getUserUseCase, createUserUseCase, getUserByIdUseCase, userFollowUseCase {
   private final getUserUseCase getUserUseCase;
    private final  createUserUseCase createUserUseCase;
    private final getUserByIdUseCase getUserByIdUseCase;
    private final userFollowUseCase addFollowerUseCase;

    public UserService(getUserUseCase getUserUseCase, createUserUseCase createUserUseCase, getUserByIdUseCase getUserByIdUseCase,  userFollowUseCase addFollowerUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.createUserUseCase = createUserUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.addFollowerUseCase = addFollowerUseCase;
    }

    @Override
    public String Follow(String userOrigen, String userDestino) {
       return addFollowerUseCase.Follow(userOrigen, userDestino);
    }

    @Override
    public UserDto createUser(User user) {
    return createUserUseCase.createUser(user);
    }
    @Override
    public Optional<UserDto> getUser(String name) {
        return getUserByIdUseCase.getUser(name);
    }
    @Override
    public List<UserDto> getUsers() {
        return getUserUseCase.getUsers();
    }
}
