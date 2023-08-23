package com.rho.cli.api.domain.doctor;

import com.rho.cli.api.domain.location.Location;
import jakarta.validation.constraints.NotNull;

public record UpdateDoctorDTO(
        @NotNull
        Long id,
        String name,
        String document,
        Location location
) {
}
