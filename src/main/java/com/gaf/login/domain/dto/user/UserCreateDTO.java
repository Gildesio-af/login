package com.gaf.login.domain.dto.user;

import com.gaf.login.domain.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record UserCreateDTO(
        @NotBlank
        @Pattern(regexp = "\\S+", message = "O campo [username] não pode ser vazio")
        String username,
        @Email(message = "O campo [email] deve ser um email válido")
        String email,
        @NotBlank
        @Length(min = 6, max = 12 , message = "O campo [password] deve ter entre 6 e 12 caracteres")
        String password)
        {
                public User toEntity() {
                        return new User(null, this.username(), this.email(), this.password(), LocalDateTime.now());
                }
        }
