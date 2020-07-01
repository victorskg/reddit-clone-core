package com.victorskg.redditclonecore.service;

import com.victorskg.redditclonecore.model.Subreddit;
import com.victorskg.redditclonecore.model.dto.SubredditDTO;
import com.victorskg.redditclonecore.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class SubredditService {

    private final SubredditRepository repository;

    @Transactional
    public SubredditDTO save(SubredditDTO subredditDTO) {
        var saved = repository.save(mapSubredditDTO(subredditDTO));
        subredditDTO.setId(saved.getId());

        return subredditDTO;
    }

    @Transactional(readOnly = true)
    public List<SubredditDTO> findALl() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(toList());
    }

    private Subreddit mapSubredditDTO(SubredditDTO subredditDTO) {
        return Subreddit.builder()
                .name(subredditDTO.getName())
                .description(subredditDTO.getDescription())
                .build();
    }

    private SubredditDTO mapToDTO(Subreddit subreddit) {
        return SubredditDTO.builder()
                .id(subreddit.getId())
                .name(subreddit.getName())
                .description(subreddit.getDescription())
                .build();
    }
}
