package com.console_network.app.post.applicationTest.serviceTest;

import com.console_network.app.post.application.Service.PostService;
import com.console_network.app.post.domain.model.Post;
import com.console_network.app.post.domain.repository.in.createPostUseCase;
import com.console_network.app.post.domain.repository.in.getPostByUserUseCase;
import com.console_network.app.post.domain.repository.in.getPostsUseCase;
import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.domain.repository.out.userRepository;
import com.console_network.app.user.infrastructure.dto.UserDto;
import com.console_network.app.post.infrastructure.Dto.DashBoardPostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PostServiceTest {

    @Mock
    private createPostUseCase createPostUseCase;


    @Mock
    private getPostByUserUseCase getPostByUserUseCase;

    @Mock
    private getPostsUseCase getPostsUseCase;

    @Mock
    private userRepository userRepository;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createPost_UserExists_PostCreated() {
        String postOwner = "testUser";
        Post post = new Post();
        post.setText("Test post");
        post.setCreatedAt(LocalTime.now());

        UserDto userDto = new UserDto();
        userDto.setName(postOwner);

        User user = new User();
        user.setName(postOwner);
        user.setPosts(new ArrayList<>());

        when(userRepository.findUserByName(postOwner)).thenReturn(Optional.of(userDto));
        when(userRepository.findUsersByName(postOwner)).thenReturn(user);

        List<Post> posts = postService.createPost(postOwner, post);

        assertEquals(1, posts.size());
        assertEquals(post, posts.get(0));
        verify(createPostUseCase, times(1)).createPost(postOwner, post);
    }

    @Test
    void createPost_UserNotFound_ThrowsException() {
        String postOwner = "unknownUser";
        Post post = new Post();

        when(userRepository.findUserByName(postOwner)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> postService.createPost(postOwner, post));
        assertEquals("User not found: " + postOwner, exception.getMessage());
    }

    @Test
    void getDashBoard_ValidUser_ReturnsFormattedPosts() {
        String userName = "testUser";
        User user = new User();
        user.setName(userName);

        User followedUser = new User();
        followedUser.setName("followedUser");
        List<User> following = new ArrayList<>();
        following.add(followedUser);
        user.setFollowing(following);

        Post post = new Post();
        post.setText("Followed user's post");
        post.setPostOwner("followedUser");
        post.setCreatedAt(LocalTime.now());

        followedUser.setPosts(List.of(post));

        when(userRepository.findUsersByName(userName)).thenReturn(user);

        List<DashBoardPostResponse> dashBoardPosts = postService.getDashBoard(userName);

        assertEquals(1, dashBoardPosts.size());
        assertEquals("Followed user's post @followedUser @"+post.getCreatedAt().format(DateTimeFormatter.ofPattern("HH:mm")), dashBoardPosts.get(0).getFormattedResponse());
    }


    @Test
    void getPostByUser_ReturnsPosts() {
        String userName = "testUser";
        List<Post> posts = List.of(new Post());

        when(getPostByUserUseCase.getPostByUser(userName)).thenReturn(posts);

        List<Post> result = postService.getPostByUser(userName);

        assertEquals(posts, result);
    }

    @Test
    void getAllPost_ReturnsAllPosts() {
        List<Post> posts = List.of(new Post());

        when(getPostsUseCase.getAllPost()).thenReturn(posts);

        List<Post> result = postService.getAllPost();

        assertEquals(posts, result);
    }
}
