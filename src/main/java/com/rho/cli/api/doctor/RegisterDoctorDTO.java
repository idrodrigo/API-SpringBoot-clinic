package com.rho.cli.api.doctor;


import com.rho.cli.api.location.LocationDTO;

public record RegisterDoctorDTO(
        String name,
        String email,
        String document,
        Specialization specialization,
        LocationDTO location
) {
}
