package com.rho.cli.api.domain.consultation;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CancelConsultationDTO(
        @NotNull
        Long id,
        @NotNull
        CancellationReason reason
) {
}
