package com.victorskg.redditclonecore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseEntity<Long> {

    @Column(name = "text")
    @NotBlank(message = "O comentário é obrigatório.")
    private String text;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
