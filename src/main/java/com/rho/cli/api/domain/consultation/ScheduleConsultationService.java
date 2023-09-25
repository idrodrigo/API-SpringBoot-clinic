package com.rho.cli.api.domain.consultation;

import com.rho.cli.api.domain.consultation.validation.CancelConsultValidator;
import com.rho.cli.api.domain.consultation.validation.ConsultValidator;
import com.rho.cli.api.domain.doctor.Doctor;
import com.rho.cli.api.domain.doctor.DoctorRepository;
import com.rho.cli.api.domain.patient.PatientRepository;
import com.rho.cli.api.infra.errors.IntegrityValidation;
import io.micrometer.observation.ObservationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleConsultationService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    List<ConsultValidator> validators;
    @Autowired
    List<CancelConsultValidator> cancelValidators;

    public ScheduleDetailsConsultationDTO schelude(ScheduleConsultationDTO data) {
        if (!patientRepository.findById(data.patientId()).isPresent()){
            throw new IntegrityValidation("Patient id not found");
        }

        if(data.doctorId()!=null
                && !doctorRepository.existsById(data.doctorId())){
            throw new IntegrityValidation("Doctor id not found");
        }

        validators.forEach(validator -> validator.validate(data));

        var patient = patientRepository.findById(data.patientId()).get();

        var doctor = selectDoctor(data);
        if (doctor == null){
            throw new IntegrityValidation("Doctor not found to this specialization and date");
        }

        var consultation = new Consultation(
                doctor,
                patient,
                data.date()
        );
        consultationRepository.save(consultation);
        return new ScheduleDetailsConsultationDTO(consultation);
    }

    private Doctor selectDoctor(ScheduleConsultationDTO data) {
        if (data.doctorId()!=null){
            return doctorRepository.getReferenceById(data.doctorId());
        }
        if (data.specialization() == null){
            throw new IntegrityValidation("Specialization is required");
        }
        return doctorRepository.selectDoctorWithSpecialization(
                data.specialization(), data.date()
        );
    }

//    public void cancel(CancelConsultationDTO cancelConsultationDTO) {
//          var consultation = consultationRepository.findById(cancelConsultationDTO.id())
//                    .orElseThrow(() -> new IntegrityValidation("Consultation id not found"));
//            cancelValidators.forEach(validator -> validator.validate(cancelConsultationDTO));
//            consultationRepository.delete(consultation);
//    }

    public void cancel(CancelConsultationDTO cancelConsultationDTO) {
        var consultation = consultationRepository.findById(cancelConsultationDTO.id())
                .orElseThrow(() -> new IntegrityValidation("Consultation id not found"));
        cancelValidators.forEach(validator -> validator.validate(cancelConsultationDTO));
        consultation.cancel(cancelConsultationDTO.reason());
        consultationRepository.save(consultation);
    }

   public Page<ScheduleDetailsConsultationDTO> findAll(Pageable page) {
        return consultationRepository.findAll(page).map(ScheduleDetailsConsultationDTO::new);
    }
}
