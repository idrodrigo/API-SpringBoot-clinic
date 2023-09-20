package com.rho.cli.api.domain.consultation.validation;

import com.rho.cli.api.domain.consultation.ScheduleConsultationDTO;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AnticipationTime implements ConsultValidator{
    public void validate(ScheduleConsultationDTO scheduleDetailsConsultationDTO) {
        var now = LocalDateTime.now();
        var consultationtime = scheduleDetailsConsultationDTO.date();
        var difference = Duration.between(now, consultationtime);
        if(difference.toMinutes() < 30){
            throw new ValidationException("The consultation must be scheduled at least 30 minutes in advance");
        }
    }
}
