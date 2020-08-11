package com.victorskg.redditclonecore.controller;

import com.victorskg.redditclonecore.model.dto.VoteDTO;
import com.victorskg.redditclonecore.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService service;

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteDTO voteDTO) {
        service.vote(voteDTO);
        return new ResponseEntity<>(OK);
    }

}
