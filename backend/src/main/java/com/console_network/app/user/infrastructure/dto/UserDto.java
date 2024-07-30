package com.console_network.app.user.infrastructure.dto;

import com.console_network.app.post.domain.model.Post;

import java.util.List;

public class UserDto {
    public UserDto(String name, List<String> following, List<String>follower, List<String> post){

        this.name=name;
        this.following=following;
        this.follower=follower;
        this.post=post;

    }
    public UserDto(){}

    private String name;
    private List<String> follower;
    private List<String> following;
    private  List<String> post;




    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;

    }
    public List<String> getFollower(){
        return follower;
    }
    public void setFollower(List<String> follower){
        this.follower=follower;

    }

    public List<String> getFollowing(){
        return following;
    }
    public void setFollowing(List<String> following){
        this.following=following;

    }
    public List<String> getPost(){
        return post;
    }
    public void setPost(List<String> post){
        this.post=post;

    }

}
