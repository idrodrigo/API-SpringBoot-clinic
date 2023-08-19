package com.rho.cli.api.controller;

import com.rho.cli.api.patient.Patient;
import com.rho.cli.api.patient.PatientRepository;
import com.rho.cli.api.patient.RegisterPatientDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
