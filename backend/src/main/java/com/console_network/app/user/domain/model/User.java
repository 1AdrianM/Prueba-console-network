package com.console_network.app.user.domain.model;

import com.console_network.app.post.domain.model.Post;

import java.util.ArrayList;
import java.util.List;

public class User {
    public User( String name, List<User>following, List<User>follower, List<Post> posts){

        this.name=name;
        this.following=following;
        this.follower=follower;
        this.posts=posts;
    }
    public User(){}

    private String name;
    private List<User> following = new ArrayList<>();
    private List<User> follower = new ArrayList<>();
    private List<Post> posts = new ArrayList<>();

    public String getName(){
       return name;
    }
    public void setName(String name){
       this.name=name;

    }
    public List<User> getFollower(){
       return follower;
    }
    public void setFollower(List<User> follower){
       this.follower=follower;

    }

    public List<User> getFollowing(){
   return following;
   }
    public void setFollowing(List<User> following){
        this.following=following;

    }

    public List<Post> getPosts(){
       return posts;
    }
    public void setPosts(List<Post> posts){
        this.posts=posts;
   }
}
