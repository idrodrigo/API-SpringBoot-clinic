package com.rho.cli.api.domain.user;

import jakarta.validation.constraints.NotNull;

public record RegisterUserDto(
        @NotNull
        String login,
        @NotNull
        String password
) {

}
