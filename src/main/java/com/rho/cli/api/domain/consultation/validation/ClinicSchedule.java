package com.rho.cli.api.domain.consultation.validation;

import com.rho.cli.api.domain.consultation.ScheduleConsultationDTO;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ClinicSchedule implements ConsultValidator{
    public void validate(ScheduleConsultationDTO scheduleConsultationDTO) {
        var sunday = scheduleConsultationDTO.date().getDayOfWeek().getValue() == 7;

        var beforeOpeningTime = scheduleConsultationDTO.date().getHour() < 7;

        var afterClosingTime = scheduleConsultationDTO.date().getHour() > 18;

        if (sunday || beforeOpeningTime || afterClosingTime) {
            throw new ValidationException("Clinic is closed, the schedule is from Monday to Saturday from 7:00 to 19:00");
        }

    }
}
