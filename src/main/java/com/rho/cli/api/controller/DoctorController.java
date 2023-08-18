package com.rho.cli.api.controller;

import com.rho.cli.api.doctor.RegisterDoctorDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @PostMapping
    public void postDoctor(@RequestBody RegisterDoctorDTO doctor) {
        System.out.println("DoctorController.postDoctor()");
        System.out.println("param = " + doctor);
    }
}
