package com.victorskg.redditclonecore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;
    private Long postId;
    private String text;
    private String userName;
    private Instant createdDate;

}
