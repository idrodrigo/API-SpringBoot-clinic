package com.rho.cli.api.infra.errors;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String patientIdNotFound) {
        super(patientIdNotFound);
    }
}
