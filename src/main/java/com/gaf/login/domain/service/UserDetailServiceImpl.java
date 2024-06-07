package com.gaf.login.domain.service;

import com.gaf.login.domain.dto.jwt.AuthDTO;
import com.gaf.login.domain.dto.jwt.TokenDTO;
import com.gaf.login.domain.entities.User;
import com.gaf.login.domain.repositories.UserRepository;
import com.gaf.login.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    public TokenDTO authenticate (AuthDTO authDTO, AuthenticationManager authenticationManager) {
        String newToken = null;
        var token = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.password());
        var authenticated = authenticationManager.authenticate(token);
        if(authenticated.isAuthenticated()) {
            var user = (User) authenticated.getPrincipal();
            newToken = tokenService.generateToken(user);
        }else{
            throw new IllegalArgumentException("User not logged in");
        }

        return new TokenDTO(newToken);
    }

}
