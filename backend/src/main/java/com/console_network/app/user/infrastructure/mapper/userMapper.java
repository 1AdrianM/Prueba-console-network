package com.console_network.app.user.infrastructure.mapper;

import com.console_network.app.post.domain.model.Post;
import com.console_network.app.user.domain.model.User;
import com.console_network.app.user.infrastructure.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class userMapper {

    public static UserDto toUserDto(User user) {
        List<String> postText = user.getPosts() != null
                ? user.getPosts().stream().map(Post::getText).collect(Collectors.toList())
                : new ArrayList<>();
        return new UserDto(
                user.getName(),
                user.getFollowing().stream().map(User::getName).collect(Collectors.toList()),
                user.getFollower().stream().map(User::getName).collect(Collectors.toList()),
                postText
        );
    }
    public static List<UserDto> toListUserDto(List<User> userList) {
        return userList.stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }
    }

