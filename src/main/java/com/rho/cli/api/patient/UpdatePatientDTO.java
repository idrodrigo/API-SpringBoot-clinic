package com.rho.cli.api.patient;

import com.rho.cli.api.location.Location;
import jakarta.validation.constraints.NotNull;

public record UpdatePatientDTO(
        @NotNull
        Long id,
        String name,
        String email,
        String phone,
        Location location
) {
}
