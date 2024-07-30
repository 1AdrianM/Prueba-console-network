package com.console_network.app.user.infrastructure.loader.adapter;
import com.console_network.app.user.infrastructure.loader.port.loadData;
import com.console_network.app.user.domain.model.User;
import jakarta.annotation.PostConstruct;
import com.console_network.app.user.domain.repository.out.userRepository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class loadDataImpl implements loadData {
    private final userRepository userRepository;

    public loadDataImpl(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void loadingData(){
        User user3 = new User("AdrianM", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        User user1 = new User("Alice2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        User user2 = new User("Bob", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        User user4 = new User("Juan3 ", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        List<User> users = Arrays.asList(user1, user2, user3);
        userRepository.addDefaultUsers(users);
    }
}
