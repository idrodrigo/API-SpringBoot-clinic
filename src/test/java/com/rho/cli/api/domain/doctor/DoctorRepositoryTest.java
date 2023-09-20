package com.rho.cli.api.domain.doctor;

import com.rho.cli.api.domain.consultation.Consultation;
import com.rho.cli.api.domain.location.LocationDTO;
import com.rho.cli.api.domain.patient.Patient;
import com.rho.cli.api.domain.patient.RegisterPatientDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Select doctor with specialization scenario 1, must return null if doctor not available")
    void selectDoctorWithSpecializationScenario1() {
        //given
        var nextMonday10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var doctor=registerDoctor("Rho","rho@mail.com","123456",Specialization.CARDIOLOGIST);
        var patient= registerPatient("juan","juan@mail.com","654321");
        registerConsult(doctor,patient,nextMonday10H);

        //when
        var freeDoctor = doctorRepository.selectDoctorWithSpecialization(
                Specialization.CARDIOLOGIST,
                nextMonday10H
        );

        //then
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Select doctor with specialization scenario 2, must return a doctor available")
    void selectDoctorWithSpecializationScenario2() {
        //given
        var nextMonday10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var doctor=registerDoctor("Rho","rho@mail.com","123456",Specialization.CARDIOLOGIST);

        //when
        var freeDoctor = doctorRepository.selectDoctorWithSpecialization(
                Specialization.CARDIOLOGIST,
                nextMonday10H
        );

        //then
        assertThat(freeDoctor).isEqualTo(doctor);
    }


    private void registerConsult(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Consultation(null, patient, doctor, date, null));
    }

    private Doctor registerDoctor(String name, String email, String document, Specialization specialization) {
        var doctor = new Doctor(dataDoctor(name, email, document, specialization));
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String document) {
        var patient = new Patient(dataPatient(name, email, document));
        em.persist(patient);
        return patient;
    }

    private RegisterDoctorDTO dataDoctor(String name, String email, String document, Specialization specialization) {
        return new RegisterDoctorDTO(
                name,
                email,
                "61999999999",
                document,
                specialization,
                dataLocation()
        );
    }

    private RegisterPatientDTO dataPatient(String name, String email, String document) {
        return new RegisterPatientDTO(
                name,
                email,
                "61999999999",
                document,
                dataLocation()
        );
    }

    private LocationDTO dataLocation() {
        return new LocationDTO(
                " loca",
                "azul",
                "acapulpo",
                "321",
                "12"
        );
    }
}