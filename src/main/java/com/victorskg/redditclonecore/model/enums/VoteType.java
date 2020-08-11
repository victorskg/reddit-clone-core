package com.victorskg.redditclonecore.model.enums;


import com.victorskg.redditclonecore.exception.RedditException;

import java.util.Arrays;

public enum VoteType {

    UPVOTE(1),
    DOWNVOTE(-1);

    private int direction;

    VoteType(int direction) {
    }

    public static VoteType fromDirection(Integer direction) {
        return Arrays.stream(VoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new RedditException("Voto não encontrado"));
    }

    public Integer getDirection() {
        return direction;
    }

}
