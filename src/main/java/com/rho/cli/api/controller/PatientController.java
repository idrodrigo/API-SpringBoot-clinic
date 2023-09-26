package com.rho.cli.api.controller;

import com.rho.cli.api.domain.location.Location;
import com.rho.cli.api.domain.patient.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1.0/patient")
@SecurityRequirement(name = "bearer-key")
//@Tag(name = "Patient", description = "Patient API to register, update and delete patients")
public class PatientController {
    @Autowired
    private com.rho.cli.api.domain.patient.PatientRepository PatientRepository;
    @PostMapping
    @Operation(
            summary = "Register a patient",
            description = "Register a patient",
            tags = {"patient", "post"})
    public ResponseEntity<ResponsePatientDTO> postPatient(
            @RequestBody
            @Valid
            RegisterPatientDTO registerPatientDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
//        System.out.println("PatientController.postPatient()");
//        System.out.println("param = " + patient);
        Patient patient = PatientRepository.save(new Patient(registerPatientDTO));
        ResponsePatientDTO responsePatientDTO = new ResponsePatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getDni(),
                new Location(
                        patient.getLocation().getAddress(),
                        patient.getLocation().getDistrict(),
                        patient.getLocation().getCity(),
                        patient.getLocation().getNumber(),
                        patient.getLocation().getComplement()
                )
        );
        URI url = uriComponentsBuilder.path("/api/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(url).body(responsePatientDTO);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a patient",
            description = "Get a patient by id",
            tags = {"patient", "get"})
    public ResponseEntity<ResponsePatientDTO> getPatient(@PathVariable Long id){
        Patient patient = PatientRepository.getReferenceById(id);
//        if (patient == null) {
//            return ResponseEntity.notFound().build();
//        }
        var responsePatientDTO = new ResponsePatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getDni(),
                new Location(
                        patient.getLocation().getAddress(),
                        patient.getLocation().getDistrict(),
                        patient.getLocation().getCity(),
                        patient.getLocation().getNumber(),
                        patient.getLocation().getComplement()
                )
        );
        return ResponseEntity.ok(responsePatientDTO);
    }

    @GetMapping
    @Operation(
            summary = "Get all active patients",
            description = "Get all active patients",
            tags = {"patient", "get"})
    public ResponseEntity<Page<PatientListDTO>> getPatients(@PageableDefault(size = 2, page = 0, sort = {"name"}) Pageable page){
//        return PatientRepository.findAll(page).map(PatientListDTO::new);
        //where isActive = true
        return ResponseEntity.ok(PatientRepository.findAllByIsActiveTrue(page).map(PatientListDTO::new));
    }
    @PatchMapping
    @Transactional
    @Operation(
            summary = "Update a patient",
            description = "Update a patient",
            tags = {"patient", "patch"})
    public ResponseEntity<ResponsePatientDTO> updatePatient(@RequestBody @Valid UpdatePatientDTO UpdatePatientDTO) {
        Patient patient= PatientRepository.getReferenceById(UpdatePatientDTO.id());
        patient.updateData(UpdatePatientDTO);
        var responsePatientDTO = new ResponsePatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getDni(),
                new Location(
                        patient.getLocation().getAddress(),
                        patient.getLocation().getDistrict(),
                        patient.getLocation().getCity(),
                        patient.getLocation().getNumber(),
                        patient.getLocation().getComplement()
                )
        );
        return ResponseEntity.ok(responsePatientDTO);
    }
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Delete a patient",
            description = "Delete a patient",
            tags = {"patient", "delete"})
    public ResponseEntity<ResponsePatientDTO> deletePatient(@PathVariable Long id) {
        Patient patient = PatientRepository.getReferenceById(id);
//        if(patient == null){
//            return ResponseEntity.notFound().build();
//        }
        PatientRepository.delete(patient);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Set a patient as active or inactive",
            description = "Set a patient as active or inactive",
            tags = {"patient", "patch"})
    public ResponseEntity<ResponsePatientDTO> setIsActive(@PathVariable Long id) {
        Patient patient = PatientRepository.getReferenceById(id);
        patient.setIsActive();
        var responsePatientDTO = new ResponsePatientDTO(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getDni(),
                new Location(
                        patient.getLocation().getAddress(),
                        patient.getLocation().getDistrict(),
                        patient.getLocation().getCity(),
                        patient.getLocation().getNumber(),
                        patient.getLocation().getComplement()
                )
        );
        return ResponseEntity.ok(responsePatientDTO);
    }
}
