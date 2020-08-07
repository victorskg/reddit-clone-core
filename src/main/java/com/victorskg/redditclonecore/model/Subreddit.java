package com.victorskg.redditclonecore.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subreddit")
@EqualsAndHashCode(callSuper = true)
public class Subreddit extends BaseEntity<Long> {

    @Column(name = "name")
    @NotNull(message = "O nome do Subreddit é obrigatório.")
    @Size(min = 3, max = 255, message = "O nome do Subreddit deve ter entre 3 e 255 caracteres.")
    private String name;

    @Column(name = "description")
    @NotBlank(message = "A descrição do Subreddit é obrigatória.")
    private String description;

    @OneToMany(fetch = LAZY)
    private List<Post> posts;

    @Column(name = "created_date")
    private Instant createdDate = Instant.now();

    @ManyToOne(fetch = LAZY)
    private User user;

}
