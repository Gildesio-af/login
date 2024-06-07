package com.gaf.login.domain.repositories;

import com.gaf.login.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<UserDetails> findByEmail(String email);
}
