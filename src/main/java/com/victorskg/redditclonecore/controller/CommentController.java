package com.victorskg.redditclonecore.controller;

import com.victorskg.redditclonecore.model.dto.CommentDTO;
import com.victorskg.redditclonecore.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("comments")
public class CommentController {

    private final CommentService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public CommentDTO create(@RequestBody CommentDTO commentDTO) {
        return service.save(commentDTO);
    }

    @GetMapping("post/{postId}")
    public List<CommentDTO> findAllPostComments(@PathVariable Long postId) {
        return service.findAllPostComments(postId);
    }

    @GetMapping("user/{username}")
    public List<CommentDTO> findAllUserComments(@PathVariable String username) {
        return service.findAllUserComments(username);
    }

}
