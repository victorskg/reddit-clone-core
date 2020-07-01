package com.victorskg.redditclonecore.exception.handler;

import com.victorskg.redditclonecore.exception.RedditException;
import com.victorskg.redditclonecore.model.APIError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RedditExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RedditException.class)
    protected ResponseEntity<APIError> handleException(RedditException exception) {
        return ResponseEntity.badRequest()
                .body(APIError.builder()
                        .message(exception.getMessage())
                        .status(400)
                        .build()
                );
    }

}
