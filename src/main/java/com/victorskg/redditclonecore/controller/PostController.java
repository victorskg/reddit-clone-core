package com.victorskg.redditclonecore.controller;

import com.victorskg.redditclonecore.model.dto.PostRequest;
import com.victorskg.redditclonecore.model.dto.PostResponse;
import com.victorskg.redditclonecore.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("posts")
public class PostController {

    private final PostService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public PostResponse create(@RequestBody PostRequest postRequest) {
        return service.save(postRequest);
    }

    @GetMapping
    public List<PostResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("{id}")
    public PostResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("subreddit/{id}")
    public List<PostResponse> findBySubreddit(@PathVariable Long id) {
        return service.findBySubreddit(id);
    }

    @GetMapping("user/{username}")
    public List<PostResponse> findBySubreddit(@PathVariable String username) {
        return service.findByUsername(username);
    }

}
