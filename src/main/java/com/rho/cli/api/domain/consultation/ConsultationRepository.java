package com.rho.cli.api.domain.consultation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Boolean existsByPatientIdAndDateBetween(Long patientID, LocalDateTime firstSchedule, LocalDateTime lastSchedule);

    Boolean existsByDoctorIdAndDate(Long doctorID, LocalDateTime date);
}
