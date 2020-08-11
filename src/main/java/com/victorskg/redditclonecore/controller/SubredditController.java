package com.victorskg.redditclonecore.controller;

import com.victorskg.redditclonecore.model.dto.SubredditDTO;
import com.victorskg.redditclonecore.service.SubredditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("subreddit")
public class SubredditController {

    private final SubredditService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public SubredditDTO create(@RequestBody SubredditDTO subredditDTO) {
         return service.save(subredditDTO);
    }

    @GetMapping
    public List<SubredditDTO> findAll() {
        return service.findALl();
    }

    @GetMapping("{id}")
    public SubredditDTO findById(@PathVariable Long id) {
        return service.findDTOById(id);
    }

}
