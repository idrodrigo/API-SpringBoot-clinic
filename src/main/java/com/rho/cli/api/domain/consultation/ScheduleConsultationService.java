package com.rho.cli.api.domain.consultation;

import com.rho.cli.api.domain.doctor.Doctor;
import com.rho.cli.api.domain.doctor.DoctorRepository;
import com.rho.cli.api.domain.patient.Patient;
import com.rho.cli.api.domain.patient.PatientRepository;
import com.rho.cli.api.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleConsultationService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ConsultationRepository consultationRepository;
    public void schelude(ScheduleConsultationDTO scheduleConsultationDTO) {
        if (!patientRepository.findById(scheduleConsultationDTO.patientId()).isPresent()){
            throw new IntegrityValidation("Patient id not found");
        }

        if(scheduleConsultationDTO.doctorId()!=null
                && !doctorRepository.existsById(scheduleConsultationDTO.doctorId())){
            throw new IntegrityValidation("Doctor id not found");
        }

        var patient = patientRepository.findById(scheduleConsultationDTO.patientId()).get();

        var doctor = selectDoctor(scheduleConsultationDTO);

        var consultation = new Consultation(
                null,
                patient,
                doctor,
                scheduleConsultationDTO.date()
        );
        consultationRepository.save(consultation);
    }

    private Doctor selectDoctor(ScheduleConsultationDTO scheduleConsultationDTO) {
        if (scheduleConsultationDTO.doctorId()!=null){
            return doctorRepository.getReferenceById(scheduleConsultationDTO.doctorId());
        }
        if (scheduleConsultationDTO.specialization() == null){
            throw new IntegrityValidation("Specialization is required");
        }
        return doctorRepository.selectDoctorWithSpecialization(
                scheduleConsultationDTO.specialization(), scheduleConsultationDTO.date()
        );
    }
}
