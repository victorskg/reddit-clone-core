package com.victorskg.redditclonecore.model.dto;

import com.victorskg.redditclonecore.model.enums.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {

    private Long postId;
    private VoteType type;

}
