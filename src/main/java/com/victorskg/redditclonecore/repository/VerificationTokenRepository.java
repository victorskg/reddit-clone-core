package com.victorskg.redditclonecore.repository;

import com.victorskg.redditclonecore.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    @Query("SELECT vt FROM VerificationToken vt WHERE vt.user.id = ?1 AND vt.token = ?2")
    Optional<VerificationToken> findByUserIdAndToken(Long userId, String token);
}
