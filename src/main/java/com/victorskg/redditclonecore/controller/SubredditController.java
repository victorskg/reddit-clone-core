package com.victorskg.redditclonecore.controller;

import com.victorskg.redditclonecore.model.dto.SubredditDTO;
import com.victorskg.redditclonecore.service.SubredditService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subreddit")
public class SubredditController {

    private final SubredditService service;

    @PostMapping
    public ResponseEntity<SubredditDTO> create(@RequestBody SubredditDTO subredditDTO) {
         return ResponseEntity.status(CREATED)
                 .body(service.save(subredditDTO));
    }

    @GetMapping
    public ResponseEntity<List<SubredditDTO>> findAll() {
        return ResponseEntity.ok(service.findALl());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findDTOById(id));
    }

}
