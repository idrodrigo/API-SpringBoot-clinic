package com.rho.cli.api.patient;

public record PatientListDTO(
        Long id,
        String name,
        String email
) {
    public PatientListDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail());
    }
}
