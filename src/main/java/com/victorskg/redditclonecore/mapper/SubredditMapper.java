package com.victorskg.redditclonecore.mapper;

import com.victorskg.redditclonecore.model.Post;
import com.victorskg.redditclonecore.model.Subreddit;
import com.victorskg.redditclonecore.model.dto.SubredditDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDTO mapSubredditToDTO(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDTOToSubreddit(SubredditDTO subredditDto);

}
