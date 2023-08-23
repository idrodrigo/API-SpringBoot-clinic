package com.rho.cli.api.domain.doctor;


import com.rho.cli.api.domain.location.LocationDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterDoctorDTO(
        @NotBlank(message = "{name.mandatory}")
        String name,
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
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
