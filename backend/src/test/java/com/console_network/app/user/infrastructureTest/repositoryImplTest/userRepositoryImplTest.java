package com.console_network.app.user.infrastructureTest.repositoryImplTest;

import com.console_network.app.user.infrastructure.repositoryImpl.userRepositoryImpl;

import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.infrastructure.InMemoryDb.InMemoryDb;
import com.console_network.app.user.infrastructure.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryImplTest {

    private userRepositoryImpl userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new userRepositoryImpl();
        // Clear the in-memory database before each test
        InMemoryDb.usersInMemory.clear();
    }

    // Helper method to create a UserDto
    private UserDto createUserDto(String name) {
        return new UserDto(name, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    // Helper method to create a User
    private User createUser(String name) {
        return new User(name, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    @Test
    void testSaveUser() {
        // Arrange
        User user = createUser("Alice");
        UserDto expectedUserDto = createUserDto("Alice");

        // Act
        UserDto actualUserDto = userRepository.saveUser(user);

        // Assert
        // Check if the user is saved in the in-memory database
        User savedUser = InMemoryDb.usersInMemory.get("Alice");
        assertNotNull(savedUser, "Nombre de usuario debio guardarse en la memoria");

        // Verify the attributes of the saved user
        assertEquals("Alice", savedUser.getName(), "User name deberia ser Alice");
        assertEquals(expectedUserDto.getFollowing(), actualUserDto.getFollowing(), "Following list");
        assertEquals(expectedUserDto.getFollower(), actualUserDto.getFollower(), "Follower list");
        assertEquals(expectedUserDto.getPost(), actualUserDto.getPost(), "Posts list ");
    }


    @Test
    void testFindAllUsers() {
        User user1 = createUser("Alice");
        User user2 = createUser("Bob");

        userRepository.saveUser(user1);
        userRepository.saveUser(user2);

        List<UserDto> users = userRepository.findAllUsers();

        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(userDto -> userDto.getName().equals("Alice")));
        assertTrue(users.stream().anyMatch(userDto -> userDto.getName().equals("Bob")));
    }

    @Test
    void testFindUserByName() {
        User user = createUser("Alice");
        userRepository.saveUser(user);

        User foundUser = userRepository.findUsersByName("Alice");

        assertNotNull(foundUser);
        assertEquals("Alice", foundUser.getName());
    }

    @Test
    void testFindUserByNameNotFound() {
        assertThrows(RuntimeException.class, () -> {
            userRepository.findUserByName("NonExistentUser");
        });
    }

    @Test
    void testAddFollowUser() {
        User user1 = createUser("Alice");
        User user2 = createUser("Bob");

        userRepository.saveUser(user1);
        userRepository.saveUser(user2);

        String result = userRepository.addFollowUser("Alice", "Bob");

        assertEquals("Alice empezo a seguir a Bob", result);
        assertTrue(user1.getFollowing().contains(user2));
        assertTrue(user2.getFollower().contains(user1));
    }

    @Test
    void testAddFollowUserNotFound() {
        User user = createUser("Alice");
        userRepository.saveUser(user);

        assertThrows(HttpClientErrorException.class, () -> {
            userRepository.addFollowUser("Alice", "NonExistentUser");
        });
    }

    @Test
    void testAddFollowSelf() {
        User user = createUser("Alice");
        userRepository.saveUser(user);

        assertThrows(HttpClientErrorException.class, () -> {
            userRepository.addFollowUser("Alice", "Alice");
        });
    }
}
