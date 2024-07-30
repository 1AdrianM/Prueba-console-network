package com.console_network.app.user.infrastructureTest.Mapper;


import com.console_network.app.post.domain.model.Post;
import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.infrastructure.mapper.userMapper;
import com.console_network.app.user.infrastructure.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class userMapperTest {

    // Helper method to create a User with posts and followers/following
    private User createUser(String name, List<User> following, List<User> followers, List<Post> posts) {
        return new User(name, following, followers, posts);
    }

    // Helper method to create a Post
    private Post createPost(UUID id, String postOwner, String text, LocalTime createdAt) {
        return new Post(id, postOwner, text, createdAt);
    }

    @Test
    void testToUserDto() {
        // Arrange
        UUID postId1 = UUID.randomUUID();
        UUID postId2 = UUID.randomUUID();
        Post post1 = createPost(postId1, "Alice", "Hello World", LocalTime.now());
        Post post2 = createPost(postId2, "Alice", "Another Post", LocalTime.now());
        List<Post> posts = List.of(post1, post2);

        User follower1 = createUser("Follower1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        User follower2 = createUser("Follower2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        List<User> followers = List.of(follower1, follower2);

        User following1 = createUser("Following1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        User following2 = createUser("Following2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        List<User> following = List.of(following1, following2);

        User user = createUser("Alice", following, followers, posts);

        // Act
        UserDto userDto = userMapper.toUserDto(user);

        // Assert
        assertEquals("Alice", userDto.getName());
        assertEquals(List.of("Following1", "Following2"), userDto.getFollowing());
        assertEquals(List.of("Follower1", "Follower2"), userDto.getFollower());
        assertEquals(List.of("Hello World", "Another Post"), userDto.getPost());
    }

    @Test
    void testToListUserDto() {
        // Arrange
        User user1 = createUser("Alice", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        User user2 = createUser("Bob", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        List<User> users = List.of(user1, user2);

        // Act
        List<UserDto> userDtos = userMapper.toListUserDto(users);

        // Assert
        assertEquals(2, userDtos.size());
        assertEquals("Alice", userDtos.get(0).getName());
        assertEquals("Bob", userDtos.get(1).getName());
    }
}
