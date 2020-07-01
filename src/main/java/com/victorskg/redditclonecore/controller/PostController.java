package com.victorskg.redditclonecore.controller;

import com.victorskg.redditclonecore.model.Post;
import com.victorskg.redditclonecore.model.dto.PostRequest;
import com.victorskg.redditclonecore.model.dto.PostResponse;
import com.victorskg.redditclonecore.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService service;

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostRequest postRequest) {
        return ResponseEntity.status(CREATED).body(service.save(postRequest));
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/subreddit/{id}")
    public ResponseEntity<List<PostResponse>> findBySubreddit(@PathVariable Long id) {
        return ResponseEntity.ok(service.findBySubreddit(id));
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostResponse>> findBySubreddit(@PathVariable String username) {
        return ResponseEntity.ok(service.findByUsername(username));
    }

}
