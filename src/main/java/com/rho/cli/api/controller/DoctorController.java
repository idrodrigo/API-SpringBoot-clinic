package com.rho.cli.api.controller;

import com.rho.cli.api.doctor.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Page<DoctorListDTO> getActiveDoctors(@PageableDefault(size = 2, page = 0, sort = {"name"}) Pageable page){
//        return DoctorRepository.findAll(page).map(DoctorListDTO::new);
        //where isActive = true
        return DoctorRepository.findAllByIsActiveTrue(page).map(DoctorListDTO::new);
    }
    @PatchMapping
    @Transactional
    public void updateDoctor(@RequestBody @Valid UpdateDoctorDTO UpdateDoctorDTO) {
        Doctor doctor= DoctorRepository.getReferenceById(UpdateDoctorDTO.id());
        doctor.updateData(UpdateDoctorDTO);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteDoctor(@PathVariable Long id) {
        Doctor doctor = DoctorRepository.findById(id).orElse(null);
        if(doctor == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor with ID " + id + " not found");
        }
        DoctorRepository.delete(doctor);
    }
    @PatchMapping("/{id}")
    @Transactional
    public void setIsActive(@PathVariable Long id) {
        Doctor doctor = DoctorRepository.getReferenceById(id);
        doctor.setIsActive();
    }
}
