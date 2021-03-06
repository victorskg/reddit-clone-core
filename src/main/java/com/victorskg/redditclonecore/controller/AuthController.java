package com.victorskg.redditclonecore.controller;

import com.victorskg.redditclonecore.model.dto.AuthenticationResponse;
import com.victorskg.redditclonecore.model.dto.LoginRequest;
import com.victorskg.redditclonecore.model.Message;
import com.victorskg.redditclonecore.model.dto.RegisterRequest;
import com.victorskg.redditclonecore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<RegisterRequest> signUp(@RequestBody RegisterRequest registerRequest) {
        authService.signUp(registerRequest);
        return new ResponseEntity<>(CREATED);
    }

    @GetMapping("{userId}/verification/{token}")
    public ResponseEntity<Message> verifyAccount(@PathVariable Long userId, @PathVariable String token) {
        authService.verifyAccount(userId, token);
        return new ResponseEntity<>(OK);
    }

    @PostMapping("login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

}
