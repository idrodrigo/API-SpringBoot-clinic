package com.rho.cli.api.doctor;

import com.rho.cli.api.location.Location;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "doctor")
@Entity(name = "doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    @Embedded
    private Location location;
    public Doctor(RegisterDoctorDTO doctor) {
        this.name = doctor.name();
        this.email = doctor.email();
        this.phone = doctor.phone();
        this.document = doctor.document();
        this.specialization = doctor.specialization();
        this.location = new Location(doctor.location());
    }
}