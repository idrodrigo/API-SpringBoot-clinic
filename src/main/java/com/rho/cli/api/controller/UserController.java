package com.rho.cli.api.controller;

import com.rho.cli.api.domain.user.RegisterUserDto;
import com.rho.cli.api.domain.user.ResposeUserDto;
import com.rho.cli.api.domain.user.User;
import com.rho.cli.api.domain.user.UserRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder PasswordEncoder;

    @PostMapping
    public ResponseEntity<ResposeUserDto> postUser(
            @RequestBody
            @Valid
            RegisterUserDto registerUserDto
    ) {
        User user = new User(
                registerUserDto.login(),
                PasswordEncoder.encode(registerUserDto.password())
        );

                userRepository.save(user);
        ResposeUserDto resposeUserDto = new ResposeUserDto(
                user.getId(),
                user.getLogin()
        );
        return ResponseEntity.ok(resposeUserDto);
    }
}
