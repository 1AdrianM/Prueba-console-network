package com.console_network.app.user.applicationTest.UseCaseTest;


import com.console_network.app.user.application.UseCase.userGetByIdUseCaseImpl;
import com.console_network.app.user.domain.repository.out.userRepository;
import com.console_network.app.user.infrastructure.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class userGetByIdUseCaseImplTest {

    @Mock
    private userRepository userRepository;

    @InjectMocks
    private userGetByIdUseCaseImpl userGetByIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUser() {
        String name = "Alice";
        UserDto userDto = new UserDto(name, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        when(userRepository.findUserByName(name)).thenReturn(Optional.of(userDto));

        Optional<UserDto> result = userGetByIdUseCase.getUser(name);

        assertEquals(Optional.of(userDto), result);
        verify(userRepository, times(1)).findUserByName(name);
    }
}
