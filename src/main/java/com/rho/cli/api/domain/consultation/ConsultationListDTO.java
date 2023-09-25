package com.rho.cli.api.domain.consultation;

import java.time.LocalDateTime;

public record ConsultationListDTO(
        Long id,
        Long patientId,
        String patientName,
        Long doctorId,
        String doctorName,
        LocalDateTime date
) {
    public ConsultationListDTO(ScheduleDetailsConsultationDTO consultation) {
        this(
                consultation.id(),
                consultation.patientId(),
                consultation.patientName(),
                consultation.doctorId(),
                consultation.doctorName(),
                consultation.date()
        );
    }
}

