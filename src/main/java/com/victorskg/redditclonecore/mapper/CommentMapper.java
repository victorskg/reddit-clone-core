package com.victorskg.redditclonecore.mapper;

import com.victorskg.redditclonecore.model.Comment;
import com.victorskg.redditclonecore.model.Post;
import com.victorskg.redditclonecore.model.User;
import com.victorskg.redditclonecore.model.dto.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "text", source = "commentDTO.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    Comment map(CommentDTO commentDTO, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentDTO mapToDTO(Comment comment);

}
