package com.console_network.app.user.domain.repository.in;

import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.infrastructure.dto.UserDto;


public interface createUserUseCase {
    UserDto createUser( User user);
}
