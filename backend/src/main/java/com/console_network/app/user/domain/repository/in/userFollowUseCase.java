package com.console_network.app.user.domain.repository.in;

import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.infrastructure.out.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface userFollowUseCase {
    String Follow(String userOrigen, String userDestino);
    Optional<List<UserDto>> getFollowers(UserDto user);


}
