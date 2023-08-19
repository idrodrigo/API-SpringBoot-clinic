package com.rho.cli.api.controller;

import com.rho.cli.api.doctor.Doctor;
import com.rho.cli.api.doctor.DoctorRepository;
import com.rho.cli.api.doctor.RegisterDoctorDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository DoctorRepository;
    @PostMapping
    public void postDoctor(@RequestBody @Valid RegisterDoctorDTO doctor) {
//        System.out.println("DoctorController.postDoctor()");
//        System.out.println("param = " + doctor);
        DoctorRepository.save(new Doctor(doctor));
    }
}
