package com.rho.cli.api.domain.consultation;

import java.time.LocalDateTime;

public record scheduleDetailsConsultationDTO(
        Long id,
        Long patientId,
        Long doctorId,
        LocalDateTime date
) {
    public scheduleDetailsConsultationDTO(Consultation consultation) {
        this(
                consultation.getId(),
                consultation.getPatient().getId(),
                consultation.getDoctor().getId(),
                consultation.getDate()
        );
    }
}
