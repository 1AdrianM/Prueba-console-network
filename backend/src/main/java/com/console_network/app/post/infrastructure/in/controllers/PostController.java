package com.console_network.app.post.infrastructure.in.controllers;
import com.console_network.app.post.domain.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.console_network.app.post.application.Service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/users/posts")
    public ResponseEntity<List<Post>> getPosts(){
    return new ResponseEntity<>(postService.getAllPost(),HttpStatus.OK);
}


    @PostMapping("user/{postOwner}/create/post")
     @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<Post>> createPost(@PathVariable("postOwner") String postOwner,@RequestBody Post post) {

        return new ResponseEntity<>(postService.createPost(postOwner,post), HttpStatus.CREATED);
    }
    @GetMapping("user/post/{id}")
        public ResponseEntity<Post> getPostById(){
        return null;
        }

        @DeleteMapping("user/delete/post/{id}")
        public String deletePost(@PathVariable("id") String id) {
        postService.deletePost(id);
        return "post deleted";
        //a arreglar
}





}

