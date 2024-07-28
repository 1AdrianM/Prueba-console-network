package com.console_network.app.post.domain.model;

import com.console_network.app.user.domain.model.User;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Post implements Cloneable {
public Post(UUID id, String postOwner,String title, String text, LocalDateTime createdAt){
this.id= UUID.randomUUID();
this.postOwner=postOwner;
 this.title=title;
 this.createdAt=createdAt;
 this.text=text;
}
    public Post(){
    }
     private UUID id;
private String postOwner;
    private String title;
    private String text;
    private LocalDateTime createdAt = LocalDateTime.now();

    @Override
    public Post clone() {
        return new Post(this.id, this.postOwner, this.title, this.text, this.createdAt);
    }

    public  UUID getId(){return id;}
    public void setId(UUID id){this.id=id;}
    public String getPostOwner(){ return postOwner;}
    public void setPostOwner(String postOwner){this.postOwner=postOwner;}
      public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text=text;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }

}
