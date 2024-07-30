package com.console_network.app.post.infrastructureTest.rest.adapter.controllers;

import com.console_network.app.post.application.Service.PostService;
import com.console_network.app.post.domain.model.Post;
import com.console_network.app.post.infrastructure.in.controllers.PostController;
import com.console_network.app.post.infrastructure.Dto.DashBoardPostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    private Post post1;
    private Post post2;
    private DashBoardPostResponse response1;
    private DashBoardPostResponse response2;

    @BeforeEach
    public void setUp() {
        post1 = new Post(UUID.randomUUID(), "Alice", "Post content 1", LocalTime.now());
        post2 = new Post(UUID.randomUUID(), "Alice", "Post content 2", LocalTime.now());
        response1 = new DashBoardPostResponse("Hola que tal @Usuario1 @12:00");
        response2 = new DashBoardPostResponse("Bien y tu @Usuario2 @8:00");
    }

    @Test
    public void testGetPosts() throws Exception {
        List<Post> posts = Arrays.asList(post1, post2);
        Mockito.when(postService.getAllPost()).thenReturn(posts);

        mockMvc.perform(get("/api/users/posts")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(posts.size()))
                .andExpect(jsonPath("$[0].text").value(post1.getText()))
                .andExpect(jsonPath("$[1].text").value(post2.getText()));
    }

    @Test
    public void testGetDashBoard() throws Exception {
        List<DashBoardPostResponse> dashboardPosts = Arrays.asList(response1, response2);
        Mockito.when(postService.getDashBoard(anyString())).thenReturn(dashboardPosts);

        mockMvc.perform(get("/api/user/Alice/dashboard")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(dashboardPosts.size()))
                .andExpect(jsonPath("$[0].formattedResponse").value(response1.getFormattedResponse()))
                .andExpect(jsonPath("$[1].formattedResponse").value(response2.getFormattedResponse()));
    }

    // Add your testCreatePost method here, ensuring that all Mockito stubs are properly completed.
    @Test
    public void testCreatePost() throws Exception {
        Post post = new Post(UUID.randomUUID(), "Alice", "New post content", LocalTime.now());
        List<Post> posts = Arrays.asList(post);
        Mockito.when(postService.createPost(anyString(), any(Post.class))).thenReturn(posts);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/Alice/create/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"postOwner\":\"Alice\", \"text\":\"New post content\", \"createdAt\":\"12:00\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.length()").value(posts.size()))
                .andExpect(jsonPath("$[0].postOwner").value(post.getPostOwner()))
                .andExpect(jsonPath("$[0].text").value(post.getText()))
                .andExpect(jsonPath("$[0].createdAt").value(post.getCreatedAt().toString()));
    }
}
