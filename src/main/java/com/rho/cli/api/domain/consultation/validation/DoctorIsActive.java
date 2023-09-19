package com.rho.cli.api.domain.consultation.validation;

import com.rho.cli.api.domain.consultation.ScheduleConsultationDTO;
import com.rho.cli.api.domain.doctor.DoctorRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorIsActive implements ConsultValidator{
    @Autowired
    private DoctorRepository doctorRepository;
    public void validate(ScheduleConsultationDTO scheduleConsultationDTO) {
        if (scheduleConsultationDTO.doctorId() == null) {
            return;
        }
        var doctor = doctorRepository.findIsActiveById(scheduleConsultationDTO.doctorId());
        if (doctor == null || !doctor) {
            throw new ValidationException("Doctor is not active");
        }
    }
}
