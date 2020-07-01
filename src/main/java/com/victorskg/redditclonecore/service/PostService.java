package com.victorskg.redditclonecore.service;

import com.victorskg.redditclonecore.exception.RedditException;
import com.victorskg.redditclonecore.mapper.PostMapper;
import com.victorskg.redditclonecore.model.Post;
import com.victorskg.redditclonecore.model.dto.PostRequest;
import com.victorskg.redditclonecore.model.dto.PostResponse;
import com.victorskg.redditclonecore.repository.PostRepository;
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
public class PostService {

    private final PostRepository repository;

    private final AuthService authService;

    private final SubredditService subredditService;

    private final PostMapper mapper;

    private final UserService userService;

    @Transactional
    public Post save(PostRequest postRequest) {
        var subreddit = subredditService.findByName(postRequest.getSubredditName());
        var currentUser = authService.getCurrentUser();

        return repository.save(mapper.map(postRequest, subreddit, currentUser));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::mapToResponse)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public PostResponse findById(Long id) {
        return repository.findById(id)
                .map(mapper::mapToResponse)
                .orElseThrow(() ->
                        new RedditException(format("Não foi possível encontrar o post de id %d.", id)));
    }

    public List<PostResponse> findBySubreddit(Long subredditId) {
        var subreddit = subredditService.findById(subredditId);

        return repository.findAllBySubreddit(subreddit)
                .stream()
                .map(mapper::mapToResponse)
                .collect(toList());
    }

    public List<PostResponse> findByUsername(String username) {
        var user = userService.findByUsername(username);

        return repository.findByUser(user)
                .stream()
                .map(mapper::mapToResponse)
                .collect(toList());
    }

}
