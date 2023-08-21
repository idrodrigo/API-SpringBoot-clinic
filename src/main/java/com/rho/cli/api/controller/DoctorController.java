package com.rho.cli.api.controller;

import com.rho.cli.api.doctor.Doctor;
import com.rho.cli.api.doctor.DoctorListDTO;
import com.rho.cli.api.doctor.DoctorRepository;
import com.rho.cli.api.doctor.RegisterDoctorDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

//    @GetMapping
//    public List<DoctorListDTO> getDoctors() {
//        return DoctorRepository.findAll().stream().map(DoctorListDTO::new).toList();
//    }
    @GetMapping
    public Page<DoctorListDTO> getDoctors(@PageableDefault(size = 2, page = 0, sort = {"name"}) Pageable page){
        return DoctorRepository.findAll(page).map(DoctorListDTO::new);
    }
}
