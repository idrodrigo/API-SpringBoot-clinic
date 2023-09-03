package com.rho.cli.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public String generateToken() {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withIssuer("rho")
                    .withSubject("rho")
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        }
    }
}
