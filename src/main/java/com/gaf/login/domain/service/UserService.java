package com.gaf.login.domain.service;

import com.gaf.login.domain.dto.user.UserCreateDTO;
import com.gaf.login.domain.entities.User;
import com.gaf.login.exceptions.UserNotFoundException;
import com.gaf.login.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserById(String id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public User createUser(UserCreateDTO newUser) {
        return userRepository.save(newUser.toEntity());
    }


}
