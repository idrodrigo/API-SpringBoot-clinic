package com.rho.cli.api.domain.consultation.validation;

import com.rho.cli.api.domain.consultation.ScheduleConsultationDTO;

public interface ConsultValidator {
     void validate(ScheduleConsultationDTO scheduleConsultationDTO);
}
