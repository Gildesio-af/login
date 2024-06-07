package com.gaf.login.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gaf.login.domain.entities.User;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {

    private final String key = "mykey";
    private final Algorithm algorithm = Algorithm.HMAC256(key);

    public String generateToken(User user) {
        return JWT.create()
                .withIssuer("Login")
                .withSubject(user.getId())
                .withExpiresAt(Instant.now().plusMillis(3600000))
                .sign(algorithm);
    }

    public String decoderToken(String token) {
        var verify = JWT.require(algorithm)
                .withIssuer("Login")
                .build()
                .verify(token);

        return verify.getSubject();
    }

}
