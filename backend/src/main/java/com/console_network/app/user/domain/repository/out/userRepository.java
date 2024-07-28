package com.console_network.app.user.domain.repository.out;

import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.infrastructure.out.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface userRepository {

     default void addMultipleUsers(List<User> users) {}
    UserDto saveUser(User user);
    List<UserDto> findAllUsers();
    User findUsersByName(String name);
    Optional<UserDto> findUserByName(String name);
    String addFollowUser(String usuarioOrigen, String usuarioDestino);
    Optional<List<UserDto>> findAllFollowers(UserDto user);
}
