package com.victorskg.redditclonecore.service;

import com.victorskg.redditclonecore.exception.RedditException;
import com.victorskg.redditclonecore.model.User;
import com.victorskg.redditclonecore.model.VerificationToken;
import com.victorskg.redditclonecore.model.dto.RegisterRequest;
import com.victorskg.redditclonecore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(RegisterRequest registerRequest, String password) {
        var user = User.of(registerRequest);
        user.setPassword(password);
        return userRepository.save(user);
    }

    @Transactional
    public void enableUser(VerificationToken verificationToken) {
        var user = findByUsername(verificationToken.getUser().getUsername());
        user.setEnabled(true);
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new RedditException("Usuário '%s' não encontrado!"));
    }

}
