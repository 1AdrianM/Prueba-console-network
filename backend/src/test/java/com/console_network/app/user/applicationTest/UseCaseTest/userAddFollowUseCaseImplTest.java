package com.console_network.app.user.applicationTest.UseCaseTest;


import com.console_network.app.user.application.UseCase.userAddFollowUseCaseImpl;
import com.console_network.app.user.domain.repository.out.userRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class userAddFollowUseCaseImplTest {

    @Mock
    private userRepository userRepository;

    @InjectMocks
    private userAddFollowUseCaseImpl userAddFollowUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFollow() {
        String userOrigen = "Alice";
        String userDestino = "Bob";
        when(userRepository.addFollowUser(userOrigen, userDestino))
                .thenReturn(userOrigen + " started following " + userDestino);

        String result = userAddFollowUseCase.Follow(userOrigen, userDestino);

        assertEquals(userOrigen + " started following " + userDestino, result);
        verify(userRepository, times(1)).addFollowUser(userOrigen, userDestino);
    }

    }
