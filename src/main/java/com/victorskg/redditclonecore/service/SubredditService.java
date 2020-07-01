package com.victorskg.redditclonecore.service;

import com.victorskg.redditclonecore.exception.RedditException;
import com.victorskg.redditclonecore.mapper.SubredditMapper;
import com.victorskg.redditclonecore.model.Subreddit;
import com.victorskg.redditclonecore.model.dto.SubredditDTO;
import com.victorskg.redditclonecore.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@AllArgsConstructor
public class SubredditService {

    private final SubredditRepository repository;

    private final SubredditMapper mapper;

    @Transactional
    public SubredditDTO save(SubredditDTO subredditDTO) {
        var saved = repository.save(mapper.mapDTOToSubreddit(subredditDTO));
        subredditDTO.setId(saved.getId());

        return subredditDTO;
    }

    @Transactional(readOnly = true)
    public List<SubredditDTO> findALl() {
        return repository.findAll()
                .stream()
                .map(mapper::mapSubredditToDTO)
                .collect(toList());
    }

    public SubredditDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::mapSubredditToDTO)
                .orElseThrow(() ->
                        new RedditException(format("Não foi possível encontrar o subreddit de id %d.", id)));
    }
}
