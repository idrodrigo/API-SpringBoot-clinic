package com.rho.cli.api.domain.consultation;

import java.time.LocalDateTime;

public record ScheduleDetailsConsultationDTO(
        Long id,
        Long patientId,
        Long doctorId,
        LocalDateTime date
) {
    public ScheduleDetailsConsultationDTO(Consultation consultation) {
        this(
                consultation.getId(),
                consultation.getPatient().getId(),
                consultation.getDoctor().getId(),
                consultation.getDate()
        );
    }
}
