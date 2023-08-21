package com.rho.cli.api.controller;

import com.rho.cli.api.patient.Patient;
import com.rho.cli.api.patient.PatientListDTO;
import com.rho.cli.api.patient.PatientRepository;
import com.rho.cli.api.patient.RegisterPatientDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientRepository PatientRepository;
    @PostMapping
    public void postPatient(@RequestBody @Valid RegisterPatientDTO patient) {
//        System.out.println("PatientController.postPatient()");
//        System.out.println("param = " + patient);
        PatientRepository.save(new Patient(patient));
    }

    @GetMapping
    public Page<PatientListDTO> getPatients(@PageableDefault(size = 2, page = 0, sort = {"name"}) Pageable page){
        return PatientRepository.findAll(page).map(PatientListDTO::new);
    }
}
