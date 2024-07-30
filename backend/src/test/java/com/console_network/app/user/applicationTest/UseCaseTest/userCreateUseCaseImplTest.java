package com.console_network.app.user.applicationTest.UseCaseTest;

import com.console_network.app.user.application.UseCase.userCreateUseCaseImpl;
import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.domain.repository.out.userRepository;
import com.console_network.app.user.infrastructure.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class userCreateUseCaseImplTest {

    @Mock
    private userRepository userRepository;

    @InjectMocks
    private userCreateUseCaseImpl userCreateUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User("Alice", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        UserDto userDto = new UserDto("Alice", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        when(userRepository.saveUser(user)).thenReturn(userDto);

        UserDto result = userCreateUseCase.createUser(user);

        assertEquals(userDto, result);
        verify(userRepository, times(1)).saveUser(user);
    }
}
