package com.console_network.app.post.domain.model;

import com.console_network.app.user.domain.model.User;

import java.time.LocalTime;
import java.util.UUID;

public class Post implements Cloneable {
public Post(UUID id, String postOwner, String text, LocalTime createdAt){
this.id= id;
this.postOwner=postOwner;

 this.createdAt=createdAt;
 this.text=text;
}
    public Post(){
    }
     private UUID id;
private String postOwner;

    private String text;
    private LocalTime createdAt;

    @Override
    public Post clone() {
        return new Post(this.id, this.postOwner, this.text, this.createdAt);
    }

    public  UUID getId(){return id;}
    public void setId(UUID id){this.id=id;}
    public String getPostOwner(){ return postOwner;}
    public void setPostOwner(String postOwner){this.postOwner=postOwner;}
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text=text;
    }

    public LocalTime getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalTime createdAt){
        this.createdAt=createdAt;
    }

}
