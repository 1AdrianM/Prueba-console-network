package com.console_network.app.user.infrastructure.rest.adapter.controller;
import com.console_network.app.user.application.Service.UserService;
import com.console_network.app.user.infrastructure.dto.FollowRequest;
import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.infrastructure.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class UserController {
    @Autowired
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/create")
    public ResponseEntity<UserDto> createUser(@RequestBody User user){ //[X]
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);

    }
    @GetMapping("/users")
    public List<UserDto> getUsers(){ //[X]
        return userService.getUsers();
    }
    @GetMapping("/user/{name}")
    public ResponseEntity<Optional<UserDto>> getUser(@PathVariable("name") String name){
        return new ResponseEntity<>(userService.getUser(name), HttpStatus.OK);
    }

@PostMapping("/user/follow/{userDestino}")
    public String Follow(@PathVariable("userDestino") String userDestino, @RequestBody FollowRequest request ) {
        return userService.Follow(request.getUserOrigen(), userDestino);

    }
}
