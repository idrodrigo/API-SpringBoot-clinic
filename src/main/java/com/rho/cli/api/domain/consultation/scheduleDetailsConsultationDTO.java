package com.rho.cli.api.domain.consultation;

import java.time.LocalDateTime;

public record scheduleDetailsConsultationDTO(
        Long id,
        Long patientId,
        Long doctorId,
        LocalDateTime date
) {
}
