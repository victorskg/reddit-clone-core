package com.victorskg.redditclonecore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubredditDTO {

    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;

}
