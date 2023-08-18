package com.rho.cli.api.controller;

import com.rho.cli.api.patient.RegisterPatientDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @PostMapping
    public void postPatient(@RequestBody RegisterPatientDTO patient) {
        System.out.println("PatientController.postPatient()");
        System.out.println("param = " + patient);
    }
}
