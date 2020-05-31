package com.victorskg.redditclonecore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private Integer code;
    private String message;

    public static Message message(Integer code, String message) {
        return new Message(code, message);
    }

}
