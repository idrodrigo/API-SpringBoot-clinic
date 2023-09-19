package com.rho.cli.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByIsActiveTrue(Pageable page);

//    @Query("""
//                select d from doctor d
//                where d.isActive=1 and d.specialization = :specialization
//                and d.id not in(select c.doctor_id from consultation  c
//                where c.`date` = :d)
//                order by rand()
//                limit 1
//            """)
//    Doctor selectDoctorWithSpecialization(Specialization specialization, LocalDateTime d);
    @Query("""
            select d from doctor d 
            where d.isActive=true 
            and 
            d.specialization = :specialization
            and d.id not in(select c.doctor from consultation  c
            where c.date = :d)
            order by rand()
            limit 1
            """)
    Doctor selectDoctorWithSpecialization(Specialization specialization, LocalDateTime d);

    @Query("""
            select d.isActive from doctor d
            where d.isActive=true
            and
            d.id = :doctorId
            """)
    Boolean findIsActiveById(Long doctorId);
}
