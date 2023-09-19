package com.rho.cli.api.domain.consultation.validation;

import com.rho.cli.api.domain.consultation.CancelConsultationDTO;

public interface CancelConsultValidator {
    void validate(CancelConsultationDTO cancelConsultationDTO);
}
