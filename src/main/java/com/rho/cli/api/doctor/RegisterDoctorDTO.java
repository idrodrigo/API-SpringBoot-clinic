package com.rho.cli.api.doctor;


import com.rho.cli.api.location.LocationDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterDoctorDTO(
        @NotBlank
        String name,
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "^[0-9]{10}$")
        String phone,
        @NotBlank
        @Pattern(regexp = "^[0-9]{4,6}$")
        String document,
        @NotNull
        Specialization specialization,
        @NotNull
        @Valid
        LocationDTO location
) {
}
