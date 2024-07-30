package com.console_network.app.user.infrastructureTest.loaderTest;


import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.domain.repository.out.userRepository;
import com.console_network.app.user.infrastructure.loader.adapter.loadDataImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;




import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;


public class loadDataImplTest {

    @Mock
    private userRepository userRepository;

    @InjectMocks
    private loadDataImpl loadDataImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadingData() {
        User user1 = new User("Alice2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        User user2 = new User("Bob", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        User user3 = new User("AdrianM", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        loadDataImpl.loadingData();

        verify(userRepository, times(1)).addDefaultUsers(argThat(users ->
                users.stream().anyMatch(user -> user.getName().equals("Alice2")) &&
                        users.stream().anyMatch(user -> user.getName().equals("Bob")) &&
                        users.stream().anyMatch(user -> user.getName().equals("AdrianM"))
        ));
    }
}

