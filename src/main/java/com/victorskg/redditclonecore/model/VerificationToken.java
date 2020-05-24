package com.victorskg.redditclonecore.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@NoArgsConstructor
@Table(name = "verification_token")
public class VerificationToken extends BaseEntity<Long> {

    @Column(name = "token")
    private String token;

    @OneToOne(fetch = LAZY)
    private User user;

    @Column(name = "expiry_date")
    private Instant expiryDate;

}
