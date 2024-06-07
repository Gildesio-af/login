package com.gaf.login.application.controller;

import com.gaf.login.domain.dto.jwt.AuthDTO;
import com.gaf.login.domain.service.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserDetailServiceImpl authenticated;
    private final AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthDTO authDTO) {
        var auth = authenticated.authenticate(authDTO, authenticationManager);
        return ResponseEntity.ok().body(auth);
    }
}
