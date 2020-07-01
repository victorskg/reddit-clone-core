package com.victorskg.redditclonecore.service;

import com.victorskg.redditclonecore.model.NotificationEmail;
import com.victorskg.redditclonecore.model.User;
import com.victorskg.redditclonecore.model.dto.AuthenticationResponse;
import com.victorskg.redditclonecore.model.dto.LoginRequest;
import com.victorskg.redditclonecore.model.dto.RegisterRequest;
import com.victorskg.redditclonecore.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;

    private final MailService mailService;

    private final JwtProvider jwtProvider;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final VerificationTokenService verificationTokenService;


    @Transactional
    public void signUp(RegisterRequest registerRequest) {
        var user = userService.save(registerRequest, passwordEncoder.encode(registerRequest.getPassword()));

        var token = verificationTokenService.generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail(
                "Por favor, ative sua conta!",
                user.getEmail(),
                "Obrigado por se registrar em Reddit Clone. Clique no link abaixo para ativar sua conta: " +
                        format("http://localhost:8081/api/auth/%s/verification/%s", user.getId(), token)));
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userService.findByUsername(principal.getUsername());
    }

    public void verifyAccount(Long userId, String token) {
        userService.enableUser(verificationTokenService.findByUserIdAndToken(userId, token));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        var token = jwtProvider.generateToken(authenticate);

        return new AuthenticationResponse(loginRequest.getUsername(), token);
    }
}
