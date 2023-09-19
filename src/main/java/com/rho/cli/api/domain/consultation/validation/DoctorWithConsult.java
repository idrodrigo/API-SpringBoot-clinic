package com.rho.cli.api.domain.consultation.validation;

import com.rho.cli.api.domain.consultation.ConsultationRepository;
import com.rho.cli.api.domain.consultation.ScheduleConsultationDTO;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithConsult implements ConsultValidator{
    @Autowired
    private ConsultationRepository repository;
    public void validate(ScheduleConsultationDTO scheduleConsultationDTO) {
        if (scheduleConsultationDTO.doctorId() == null) {
            return;
        }
        var doctor = repository.existsByDoctorIdAndDate(scheduleConsultationDTO.doctorId(), scheduleConsultationDTO.date());
        if (doctor) {
            throw new ValidationException("Doctor is not available in this date and time");
        }
    }
}
