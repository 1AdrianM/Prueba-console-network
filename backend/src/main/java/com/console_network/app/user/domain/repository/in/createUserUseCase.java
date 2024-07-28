package com.console_network.app.user.domain.repository.in;

import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.infrastructure.out.dto.UserDto;


public interface createUserUseCase {
    UserDto createUser( User user);
}
