package com.rho.cli.api.patient;

import com.rho.cli.api.location.Location;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "patient")
@Entity(name = "patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isActive;
    private String name;
    private String email;
    private String phone;
    private String dni;
    @Embedded
    private Location location;

    public Patient(RegisterPatientDTO patient) {
        this.name = patient.name();
        this.email = patient.email();
        this.phone = patient.phone();
        this.dni = patient.dni();
        this.location = new Location(patient.location());
    }
    public void updateData(UpdatePatientDTO updatePatientDTO) {
        if(updatePatientDTO.name() != null){
            this.name = updatePatientDTO.name();
        }
        if(updatePatientDTO.email() != null){
            this.email = updatePatientDTO.email();
        }
        if(updatePatientDTO.phone() != null){
            this.phone = updatePatientDTO.phone();
        }
        if(updatePatientDTO.location() != null){
            this.location = this.location.updateData(updatePatientDTO.location());
        }
    }
    public void setIsActive() {
        this.isActive = !this.isActive;
    }
}
