package com.rho.cli.api.controller;

import com.rho.cli.api.patient.*;
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
//        return PatientRepository.findAll(page).map(PatientListDTO::new);
        //where isActive = true
        return PatientRepository.findAllByIsActiveTrue(page).map(PatientListDTO::new);
    }
    @PatchMapping
    @Transactional
    public void updatePatient(@RequestBody @Valid UpdatePatientDTO UpdatePatientDTO) {
        Patient patient= PatientRepository.getReferenceById(UpdatePatientDTO.id());
        patient.updateData(UpdatePatientDTO);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void deletePatient(@PathVariable Long id) {
        Patient patient = PatientRepository.findById(id).orElse(null);
        if(patient == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with ID " + id + " not found");
        }
        PatientRepository.delete(patient);
    }
    @PatchMapping("/{id}")
    @Transactional
    public void setIsActive(@PathVariable Long id) {
        Patient patient = PatientRepository.getReferenceById(id);
        patient.setIsActive();
    }
}
