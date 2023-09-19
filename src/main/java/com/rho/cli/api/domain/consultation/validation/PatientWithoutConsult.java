package com.rho.cli.api.domain.consultation.validation;

import com.rho.cli.api.domain.consultation.ConsultationRepository;
import com.rho.cli.api.domain.consultation.ScheduleConsultationDTO;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithoutConsult implements ConsultValidator{
    @Autowired
    private ConsultationRepository repository;
    public void validate(ScheduleConsultationDTO scheduleConsultationDTO) {
        var firstSchedule = scheduleConsultationDTO.date().withHour(7);
        var lastSchedule = scheduleConsultationDTO.date().withHour(18);

        var patientWithoutConsult = repository.existsByPatientIdAndDateBetween(scheduleConsultationDTO.patientId(), firstSchedule, lastSchedule);
        if (patientWithoutConsult) {
            throw new ValidationException("Patient already has a consultation scheduled for this date");
        }

    }
}
