package com.rho.cli.api.doctor;

public record DoctorListDTO(
        String name,
        String email,
        String document,
        String specialization
) {
    public DoctorListDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getDocument(), doctor.getSpecialization().toString());
    }
}
