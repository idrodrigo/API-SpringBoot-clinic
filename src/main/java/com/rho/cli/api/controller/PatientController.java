package com.rho.cli.api.controller;

import com.rho.cli.api.domain.location.Location;
import com.rho.cli.api.domain.patient.*;
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
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private com.rho.cli.api.domain.patient.PatientRepository PatientRepository;
    @PostMapping
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
    public ResponseEntity<Page<PatientListDTO>> getPatients(@PageableDefault(size = 2, page = 0, sort = {"name"}) Pageable page){
//        return PatientRepository.findAll(page).map(PatientListDTO::new);
        //where isActive = true
        return ResponseEntity.ok(PatientRepository.findAllByIsActiveTrue(page).map(PatientListDTO::new));
    }
    @PatchMapping
    @Transactional
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
