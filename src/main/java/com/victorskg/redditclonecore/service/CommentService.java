package com.victorskg.redditclonecore.service;

import com.victorskg.redditclonecore.mapper.CommentMapper;
import com.victorskg.redditclonecore.model.Comment;
import com.victorskg.redditclonecore.model.NotificationEmail;
import com.victorskg.redditclonecore.model.Post;
import com.victorskg.redditclonecore.model.User;
import com.victorskg.redditclonecore.model.dto.CommentDTO;
import com.victorskg.redditclonecore.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper mapper;
    private final UserService userService;
    private final MailService mailService;
    private final PostService postService;
    private final CommentRepository repository;

    @Transactional
    public CommentDTO save(CommentDTO commentDTO) {
        var post = postService.findPostById(commentDTO.getPostId());
        var user = userService.findByUsername(commentDTO.getUserName());
        var comment = repository.save(mapper.map(commentDTO, post, user));

        sendCommentNotification(post, user);

        return mapper.mapToDTO(comment);
    }

    public List<CommentDTO> findAllPostComments(Long postId) {
        var post = postService.findPostById(postId);

        return repository.findByPost(post)
                .stream()
                .map(mapper::mapToDTO)
                .collect(toList());
    }

    public List<CommentDTO> findAllUserComments(String username) {
        var user = userService.findByUsername(username);

        return repository.findByUser(user)
                .stream()
                .map(mapper::mapToDTO)
                .collect(toList());
    }

    private void sendCommentNotification(Post post, User commentAuthor) {
        var subject = format("Novo comentário no post %s.", post.getName());
        var message = format("%s comentou no seu post. Endereço: %s.", commentAuthor.getUsername(), post.getUrl());

        mailService.sendMail(new NotificationEmail(subject, post.getUser().getEmail(), message));
    }

}
