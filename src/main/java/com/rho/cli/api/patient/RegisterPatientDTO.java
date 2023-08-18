package com.rho.cli.api.patient;

import com.rho.cli.api.location.LocationDTO;

public record RegisterPatientDTO(
        String name,
        String email,
        String phone,
        String id,
        LocationDTO location
) {
}
