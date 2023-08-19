package com.rho.cli.api.location;

import jakarta.validation.constraints.NotBlank;

public record LocationDTO(
        @NotBlank
        String address,
        @NotBlank
        String district,
        @NotBlank
        String city,
        @NotBlank
        String number,
        @NotBlank
        String complement
) {
}
