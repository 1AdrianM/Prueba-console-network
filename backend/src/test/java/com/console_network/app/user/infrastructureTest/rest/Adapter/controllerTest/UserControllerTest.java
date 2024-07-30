package com.console_network.app.user.infrastructureTest.rest.Adapter.controllerTest;

import com.console_network.app.user.application.Service.UserService;
import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.infrastructure.rest.adapter.controller.UserController;
import com.console_network.app.user.infrastructure.dto.FollowRequest;
import com.console_network.app.user.infrastructure.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }
    @Test
    void testCreateUser() throws Exception {
        User user = new User("Alice", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        UserDto userDto = new UserDto("Alice", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        // Mock the service layer
        when(userService.createUser(any(User.class))).thenReturn(userDto);

        // Perform the request
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andReturn();

        // Validate response body
        String responseBody = result.getResponse().getContentAsString();
        System.out.println("Response body: " + responseBody);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.following").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.follower").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.post").isArray());
    }


    @Test
    void testGetUsers() throws Exception {
        UserDto userDto1 = new UserDto("Alice", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        UserDto userDto2 = new UserDto("Bob", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        List<UserDto> userDtoList = List.of(userDto1, userDto2);

        when(userService.getUsers()).thenReturn(userDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Alice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Bob"));

        verify(userService, times(1)).getUsers();
    }

    @Test
    void testGetUser() throws Exception {
        UserDto userDto = new UserDto("Alice", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        when(userService.getUser("Alice")).thenReturn(Optional.of(userDto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{name}", "Alice")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alice"));

        verify(userService, times(1)).getUser("Alice");
    }


    @Test
    void testFollow() throws Exception {
        FollowRequest request = new FollowRequest("Alice", "Bob");

        when(userService.Follow("Alice", "Bob")).thenReturn("Alice empezo a seguir a Bob");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/follow/{userDestino}", "Bob")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Alice empezo a seguir a Bob"));

        verify(userService, times(1)).Follow("Alice", "Bob");
    }
}
