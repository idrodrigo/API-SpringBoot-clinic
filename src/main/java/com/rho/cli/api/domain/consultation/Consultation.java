package com.rho.cli.api.domain.consultation;

import com.rho.cli.api.domain.doctor.Doctor;
import com.rho.cli.api.domain.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "consultation")
@Entity(name = "consultation")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    private LocalDateTime date;

    @Column(name = "cancelation_reason")
    @Enumerated(EnumType.STRING)
    private CancellationReason cancellationReason;

    public Consultation(Doctor doctor, Patient patient, LocalDateTime date) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
    }

    public void cancel(CancellationReason reason) {
        this.cancellationReason = reason;
    }
}
