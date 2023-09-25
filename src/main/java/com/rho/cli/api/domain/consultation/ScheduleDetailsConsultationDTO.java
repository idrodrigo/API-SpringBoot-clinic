package com.rho.cli.api.domain.consultation;

import java.time.LocalDateTime;

public record ScheduleDetailsConsultationDTO(
        Long id,
        Long patientId,
        String patientName,
        Long doctorId,
        String doctorName,
        LocalDateTime date
) {
    public ScheduleDetailsConsultationDTO(Consultation consultation) {
        this(
                consultation.getId(),
                consultation.getPatient().getId(),
                consultation.getPatient().getName(),
                consultation.getDoctor().getId(),
                consultation.getDoctor().getName(),
                consultation.getDate()
        );
    }
}
