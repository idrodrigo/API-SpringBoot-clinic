package com.rho.cli.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rho.cli.api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {
    @Value("${api.security.token.secret-key}")
    private String apiSecretKey;
    @Value("${api.security.token.expiration-time}")
    private int tokenExpirationTime;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecretKey);
            String token = JWT.create()
                    .withIssuer("rho")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(getExpirationTime())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Instant getExpirationTime() {
        return Instant.now().plusSeconds(tokenExpirationTime); //2hrs
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verify = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecretKey);
            verify = JWT.require(algorithm)
                    .withIssuer("rho")
                    .build()
                    .verify(token);
            verify.getSubject();
        } catch (JWTCreationException exception) {
            throw new RuntimeException(exception);
        }
        if (verify.getSubject() == null) throw new RuntimeException("Invalid token");
        return verify.getSubject();
    }
}
