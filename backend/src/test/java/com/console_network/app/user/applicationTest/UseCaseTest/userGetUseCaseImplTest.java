package com.console_network.app.user.applicationTest.UseCaseTest;

import com.console_network.app.user.application.UseCase.userGetUseCaseImpl;
import com.console_network.app.user.domain.repository.out.userRepository;
import com.console_network.app.user.infrastructure.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class userGetUseCaseImplTest {

    @Mock
    private userRepository userRepository;

    @InjectMocks
    private userGetUseCaseImpl userGetUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsers() {
        // Preparar datos de prueba
        UserDto userDto1 = new UserDto("Alice", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        UserDto userDto2 = new UserDto("Bob", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        List<UserDto> userDtoList = List.of(userDto1, userDto2);

        // Configurar el mock
        when(userRepository.findAllUsers()).thenReturn(userDtoList);

        // Ejecutar el método bajo prueba
        List<UserDto> result = userGetUseCase.getUsers();

        // Verificar los resultados
        assertEquals(userDtoList, result);
        verify(userRepository, times(1)).findAllUsers();
    }
}
