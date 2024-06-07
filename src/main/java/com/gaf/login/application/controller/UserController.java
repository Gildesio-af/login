package com.gaf.login.application.controller;

import com.gaf.login.domain.dto.user.UserCreateDTO;
import com.gaf.login.domain.entities.User;
import com.gaf.login.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User user = userService.findUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateDTO user) {
        User newUser = userService.createUser(user);

        URI location = URI.create("/users/" + newUser.getId());

        return ResponseEntity.created(location).body(newUser);
    }
}
