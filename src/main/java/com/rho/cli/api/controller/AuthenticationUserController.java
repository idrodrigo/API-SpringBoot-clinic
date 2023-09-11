package com.rho.cli.api.controller;

import com.rho.cli.api.domain.user.User;
import com.rho.cli.api.domain.user.UserAuthenticationDTO;
import com.rho.cli.api.infra.security.JWTtokenDTO;
import com.rho.cli.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class    AuthenticationUserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticationUser(
            @RequestBody
            @Valid
            UserAuthenticationDTO userAuthenticationDTO
    ) {
        Authentication authtoken = new UsernamePasswordAuthenticationToken(
                userAuthenticationDTO.login(),
                userAuthenticationDTO.password()
        );
        var authUser = authenticationManager.authenticate(authtoken).getPrincipal();
        var JWTtoken = tokenService.generateToken((User) authUser);
        return ResponseEntity.ok(new JWTtokenDTO(JWTtoken));
    }
}
