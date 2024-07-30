package com.console_network.app.user.applicationTest.ServiceTest;

import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.domain.repository.in.*;
import com.console_network.app.user.infrastructure.dto.UserDto;
import com.console_network.app.user.application.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private getUserUseCase getUserUseCase;

    @Mock
    private createUserUseCase createUserUseCase;

    @Mock
    private getUserByIdUseCase getUserByIdUseCase;

    @Mock
    private userFollowUseCase addFollowerUseCase;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFollow() {
        String userOrigen = "user1";
        String userDestino = "user2";
        when(addFollowerUseCase.Follow(userOrigen, userDestino)).thenReturn("Followed successfully");

        String result = userService.Follow(userOrigen, userDestino);

        assertEquals("Followed successfully", result);
        verify(addFollowerUseCase, times(1)).Follow(userOrigen, userDestino);
    }

    @Test
    void testCreateUser() {
        User user = new User();
        UserDto userDto = new UserDto();
        when(createUserUseCase.createUser(user)).thenReturn(userDto);

        UserDto result = userService.createUser(user);

        assertEquals(userDto, result);
        verify(createUserUseCase, times(1)).createUser(user);
    }

    @Test
    void testGetUser() {
        String name = "user1";
        Optional<UserDto> userDto = Optional.of(new UserDto());
        when(getUserByIdUseCase.getUser(name)).thenReturn(userDto);

        Optional<UserDto> result = userService.getUser(name);

        assertEquals(userDto, result);
        verify(getUserByIdUseCase, times(1)).getUser(name);
    }

    @Test
    void testGetUsers() {
        List<UserDto> userDtos = List.of(new UserDto());
        when(getUserUseCase.getUsers()).thenReturn(userDtos);

        List<UserDto> result = userService.getUsers();

        assertEquals(userDtos, result);
        verify(getUserUseCase, times(1)).getUsers();
    }

}
