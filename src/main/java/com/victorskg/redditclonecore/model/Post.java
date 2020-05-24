package com.victorskg.redditclonecore.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@EqualsAndHashCode(callSuper = true)
public class Post extends BaseEntity<Long> {

    @Column(name = "name")
    @NotNull(message = "O nome do Post é obrigatório.")
    @Size(min = 3, max = 255, message = "O nome do Post deve ter entre 3 e 255 caracteres.")
    private String name;

    @Nullable
    @Column(name = "url")
    private String url;

    @Lob
    @Nullable
    @Column(name = "description")
    private String description;

    @Column(name = "vote_count")
    private Integer voteCount = 0;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "subreddit_id", referencedColumnName = "id")
    private Subreddit subreddit;

}
