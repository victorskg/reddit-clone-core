package com.victorskg.redditclonecore.service;

import com.victorskg.redditclonecore.exception.RedditException;
import com.victorskg.redditclonecore.model.NotificationEmail;
import com.victorskg.redditclonecore.model.User;
import com.victorskg.redditclonecore.model.VerificationToken;
import com.victorskg.redditclonecore.model.dto.RegisterRequest;
import com.victorskg.redditclonecore.repository.UserRepository;
import com.victorskg.redditclonecore.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static java.lang.String.format;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    private final MailService mailService;

    @Transactional
    public void signUp(RegisterRequest registerRequest) {
        var user = User.of(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user = userRepository.save(user);

        var token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail(
                "Por favor, ative sua conta!",
                user.getEmail(),
                "Obrigado por se registrar em Reddit Clone. Clique no link abaixo para ativar sua conta: " +
                        format("http://localhost:8081/api/auth/%s/verification/%s", user.getId(), token)));
    }

    public void verifyAccount(Long userId, String token) {
        var optionalToken = verificationTokenRepository.findByUserAndToken(userId, token);
        optionalToken.orElseThrow(() -> new RedditException("Token inválido! Verifique o token e tente novamente."));
        enableUser(optionalToken.get());
    }

    @Transactional
    public void enableUser(VerificationToken verificationToken) {
        var user = userRepository
                .findByUsername(verificationToken.getUser().getUsername())
                .orElseThrow(() -> new RedditException("Usuário '%s' não encontrado!"));
        user.setEnabled(true);
        userRepository.save(user);
    }

    private String generateVerificationToken(User user) {
        var token = UUID.randomUUID().toString();
        var verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
