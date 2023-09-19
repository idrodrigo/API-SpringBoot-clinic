package com.rho.cli.api.domain.consultation.validation;

import com.rho.cli.api.domain.consultation.ScheduleConsultationDTO;
import com.rho.cli.api.domain.patient.PatientRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientIsActive implements ConsultValidator{
    @Autowired
    private PatientRepository patientRepository;
    public void validate(ScheduleConsultationDTO scheduleConsultationDTO) {
      if (scheduleConsultationDTO.patientId() == null) {
            return;
        }
        var patient = patientRepository.findIsActiveById(scheduleConsultationDTO.patientId());
        if (patient == null || !patient) {
            throw new ValidationException("Patient is not active");
        }
       }
}
