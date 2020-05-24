package com.victorskg.redditclonecore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity<Long> {

    @Column(name = "username")
    @NotNull(message = "O nome de usuário é obrigatório.")
    @Size(min = 3, max = 50, message = "O nome de usuário deve ter entre 3 e 50 caracteres.")
    private String username;

    @Column(name = "password")
    @NotNull(message = "A senha é obrigatória.")
    @Size(min = 6, max = 18, message = "A senha deve ter entre 6 e 18 caracteres.")
    private String password;

    @Email
    @NotEmpty(message = "O email é obrigatório")
    private String email;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "enabled")
    private boolean enabled;

}
