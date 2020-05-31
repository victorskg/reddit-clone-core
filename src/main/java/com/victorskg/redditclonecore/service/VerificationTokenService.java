package com.victorskg.redditclonecore.service;

import com.victorskg.redditclonecore.exception.RedditException;
import com.victorskg.redditclonecore.model.User;
import com.victorskg.redditclonecore.model.VerificationToken;
import com.victorskg.redditclonecore.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class VerificationTokenService {

    private final VerificationTokenRepository repository;

    public VerificationToken save(VerificationToken verificationToken) {
        return repository.save(verificationToken);
    }

    public String generateVerificationToken(User user) {
        var token = UUID.randomUUID().toString();
        var verificationToken = new VerificationToken(token, user);
        save(verificationToken);
        return token;
    }

    public VerificationToken findByUserIdAndToken(Long userId, String token) {
        var optionalVerificationToken = repository.findByUserIdAndToken(userId, token);
        return optionalVerificationToken.orElseThrow(() ->
                new RedditException("Token inválido! Verifique o token e tente novamente."));
    }

}
