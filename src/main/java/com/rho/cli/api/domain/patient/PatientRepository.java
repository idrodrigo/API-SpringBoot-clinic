package com.rho.cli.api.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long>{
    Page<Patient> findAllByIsActiveTrue(Pageable page);

    @Query(
            """
            select p.isActive from patient p
            where p.isActive=true
            and
            p.id = :PatientId
            """
    )
    Boolean findIsActiveById(Long PatientId);
}
