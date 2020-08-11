package com.victorskg.redditclonecore.service;

import com.victorskg.redditclonecore.model.dto.VoteDTO;
import com.victorskg.redditclonecore.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository repository;

    public void vote(VoteDTO voteDTO) {
    }

}
