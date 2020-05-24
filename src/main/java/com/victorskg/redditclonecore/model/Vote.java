package com.victorskg.redditclonecore.model;

import com.victorskg.redditclonecore.model.enums.VoteType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vote")
@EqualsAndHashCode(callSuper = true)
public class Vote extends BaseEntity<Long> {

    @Column(name = "vote_type")
    @NotNull(message = "O tipo do voto é obrigatório.")
    private VoteType voteType;

    @ManyToOne(fetch = LAZY)
    @NotNull(message = "O Post é obrigatório.")
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
