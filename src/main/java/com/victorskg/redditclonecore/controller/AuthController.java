package com.victorskg.redditclonecore.controller;

import com.victorskg.redditclonecore.model.Message;
import com.victorskg.redditclonecore.model.dto.RegisterRequest;
import com.victorskg.redditclonecore.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<Message> signUp(@RequestBody RegisterRequest registerRequest) {
        authService.signUp(registerRequest);
        return new ResponseEntity<>(Message.success("Usuário registrado com sucesso!"), OK);
    }

    @GetMapping("{userId}/verification/{token}")
    public ResponseEntity<Message> verifyAccount(@PathVariable Long userId, @PathVariable String token) {
        authService.verifyAccount(userId, token);
        return new ResponseEntity<>(Message.success("Conta ativada com sucesso!"), OK);
    }
}
