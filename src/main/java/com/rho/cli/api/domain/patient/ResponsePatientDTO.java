package com.rho.cli.api.domain.patient;

import com.rho.cli.api.domain.location.Location;

public record ResponsePatientDTO(
        Long id,
        String name,
        String email,
        String phone,
        String dni,
        Location location
) {
}
