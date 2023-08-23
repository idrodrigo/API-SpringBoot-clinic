package com.rho.cli.api.doctor;

import com.rho.cli.api.location.Location;

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
