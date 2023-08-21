package com.rho.cli.api.patient;

public record PatientListDTO(
        String name,
        String email
) {
    public PatientListDTO(Patient patient) {
        this(patient.getName(), patient.getEmail());
    }
}
