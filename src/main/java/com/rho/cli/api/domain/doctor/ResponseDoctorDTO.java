package com.rho.cli.api.domain.doctor;

import com.rho.cli.api.domain.location.Location;

public record ResponseDoctorDTO(
        Long id,
        String name,
        String email,
        String Phone,
        String document,
        Specialization specialization,
        Location location
) {

}
