package com.victorskg.redditclonecore.exception;

public class RedditException extends RuntimeException {

    public RedditException(String message) {
        super(message);
    }

    public RedditException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

}
