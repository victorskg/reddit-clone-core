package com.victorskg.redditclonecore.mapper;

import com.victorskg.redditclonecore.model.Post;
import com.victorskg.redditclonecore.model.Subreddit;
import com.victorskg.redditclonecore.model.User;
import com.victorskg.redditclonecore.model.dto.PostRequest;
import com.victorskg.redditclonecore.model.dto.PostResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "voteCount", constant = "0")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "postName", source = "name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "subredditName", source = "subreddit.name")
    PostResponse mapToResponse(Post post);

}
