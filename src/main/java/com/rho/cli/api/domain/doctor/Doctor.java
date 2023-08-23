package com.rho.cli.api.domain.doctor;

import com.rho.cli.api.domain.location.Location;
import jakarta.persistence.*;
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
    private Boolean isActive;
    private String name;
    private String email;
    private String phone;
    private String document;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    @Embedded
    private Location location;
    public Doctor(RegisterDoctorDTO doctor) {
        this.isActive = true;
        this.name = doctor.name();
        this.email = doctor.email();
        this.phone = doctor.phone();
        this.document = doctor.document();
        this.specialization = doctor.specialization();
        this.location = new Location(doctor.location());
    }

    public void updateData(UpdateDoctorDTO updateDoctorDTO) {
        if(updateDoctorDTO.name() != null){
            this.name = updateDoctorDTO.name();
        }
        if(updateDoctorDTO.document() != null){
            this.document = updateDoctorDTO.document();
        }
        if(updateDoctorDTO.location() != null){
            this.location = this.location.updateData(updateDoctorDTO.location());
        }
    }

    public void setIsActive() {
        this.isActive = !this.isActive;
    }
}