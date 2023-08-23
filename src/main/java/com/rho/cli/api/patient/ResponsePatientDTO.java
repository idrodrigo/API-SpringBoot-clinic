package com.rho.cli.api.patient;

import com.rho.cli.api.location.Location;

public record ResponsePatientDTO(
        Long id,
        String name,
        String email,
        String phone,
        String dni,
        Location location
) {
}
