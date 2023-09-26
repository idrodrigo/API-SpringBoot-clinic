package com.rho.cli.api.controller;

import com.rho.cli.api.domain.doctor.*;
import com.rho.cli.api.domain.location.Location;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1.0/doctor")
//@Tag(name = "Doctor", description = "Doctor API to register, update and delete doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {
    @Autowired
    private com.rho.cli.api.domain.doctor.DoctorRepository DoctorRepository;
    @PostMapping
    @Operation(
            summary = "Register a doctor",
            description = "Register a doctor",
            tags = {"doctor", "post"})
    public ResponseEntity<ResponseDoctorDTO> postDoctor(
            @RequestBody
            @Valid
            RegisterDoctorDTO registerDoctorDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
//        System.out.println("DoctorController.postDoctor()");
//        System.out.println("param = " + doctor);
        Doctor doctor = DoctorRepository.save(new Doctor(registerDoctorDTO));
        //return 201 created
        // get http://localhost:8080/api/doctor/1
       ResponseDoctorDTO responseDoctorDTO = new ResponseDoctorDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getDocument(),
                doctor.getSpecialization(),
                new Location(
                        doctor.getLocation().getAddress(),
                        doctor.getLocation().getDistrict(),
                        doctor.getLocation().getCity(),
                        doctor.getLocation().getNumber(),
                        doctor.getLocation().getComplement()
                )
        );
//      URI url = URI.create("http://localhost:8080/api/doctor/" + doctor.getId());
        URI url = uriComponentsBuilder.path("/api/doctor/{id}").buildAndExpand(doctor.getId()).toUri();
       return ResponseEntity.created(url).body(responseDoctorDTO);
    }
//    @GetMapping
//    public List<DoctorListDTO> getDoctors() {
//        return DoctorRepository.findAll().stream().map(DoctorListDTO::new).toList();
//    }
    @GetMapping
    @Operation(
            summary = "Get all active doctors",
            description = "Get all active doctors",
            tags = {"doctor", "get"})
    public ResponseEntity<Page<DoctorListDTO>> getActiveDoctors(@PageableDefault(size = 2, page = 0, sort = {"name"}) Pageable page){
//        return DoctorRepository.findAll(page).map(DoctorListDTO::new);
        //where isActive = true
        return ResponseEntity.ok(DoctorRepository.findAllByIsActiveTrue(page).map(DoctorListDTO::new));
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Get a doctor by ID",
            description = "Get a doctor by ID",
            tags = {"doctor", "get"})
    public ResponseEntity<ResponseDoctorDTO> getDoctor(@PathVariable Long id) {
        Doctor doctor = DoctorRepository.getReferenceById(id);
//        if (doctor == null) {
//            return ResponseEntity.notFound().build();
//        }
        var responseDoctorDTO = new ResponseDoctorDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getDocument(),
                doctor.getSpecialization(),
                new Location(
                        doctor.getLocation().getAddress(),
                        doctor.getLocation().getDistrict(),
                        doctor.getLocation().getCity(),
                        doctor.getLocation().getNumber(),
                        doctor.getLocation().getComplement()
                )
        );
        return ResponseEntity.ok(responseDoctorDTO);
    }
    @PatchMapping
    @Transactional
    @Operation(
            summary = "Update a doctor",
            description = "Update a doctor",
            tags = {"doctor", "patch"})
    public ResponseEntity<ResponseDoctorDTO> updateDoctor(@RequestBody @Valid UpdateDoctorDTO UpdateDoctorDTO) {
        Doctor doctor= DoctorRepository.getReferenceById(UpdateDoctorDTO.id());
        doctor.updateData(UpdateDoctorDTO);
        return ResponseEntity.ok(new ResponseDoctorDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getDocument(),
                doctor.getSpecialization(),
                new Location(
                        doctor.getLocation().getAddress(),
                        doctor.getLocation().getDistrict(),
                        doctor.getLocation().getCity(),
                        doctor.getLocation().getNumber(),
                        doctor.getLocation().getComplement()
                        )
        ));
    }
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @Transactional
    @Operation(
            summary = "Delete a doctor",
            description = "Delete a doctor",
            tags = {"doctor", "delete"})
    public ResponseEntity<ResponseDoctorDTO> deleteDoctor(@PathVariable Long id) {
        Doctor doctor = DoctorRepository.findById(id).orElse(null);
        if(doctor == null){
            return ResponseEntity.notFound().build();
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor with ID " + id + " not found");
        }
        DoctorRepository.delete(doctor);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Set a doctor as active or inactive",
            description = "Set a doctor as active or inactive",
            tags = {"doctor", "patch"})
    public ResponseEntity<ResponseDoctorDTO> setIsActive(@PathVariable Long id) {
        Doctor doctor = DoctorRepository.getReferenceById(id);
        doctor.setIsActive();
        var responseDoctorDTO = new ResponseDoctorDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getDocument(),
                doctor.getSpecialization(),
                new Location(
                        doctor.getLocation().getAddress(),
                        doctor.getLocation().getDistrict(),
                        doctor.getLocation().getCity(),
                        doctor.getLocation().getNumber(),
                        doctor.getLocation().getComplement()
                )
        );
        return ResponseEntity.ok(responseDoctorDTO);
    }
}
