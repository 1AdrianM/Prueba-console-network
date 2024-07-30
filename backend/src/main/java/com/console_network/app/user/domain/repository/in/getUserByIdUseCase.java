package com.console_network.app.user.domain.repository.in;

import com.console_network.app.user.infrastructure.dto.UserDto;

import java.util.Optional;

public interface getUserByIdUseCase {
Optional<UserDto> getUser(String name);
}
