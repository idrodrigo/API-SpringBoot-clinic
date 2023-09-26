package com.rho.cli.api.controller;

import com.rho.cli.api.domain.consultation.*;
import com.rho.cli.api.infra.errors.IntegrityValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/api/v1.0/consultation")
//@Tag(name = "Consultation", description = "Consultation API to schedule and cancel consultations")
@SecurityRequirement(name = "bearer-key")
public class ConsultationController {
    @Autowired
    private ScheduleConsultationService service;
    @PostMapping
    @Transactional
    @Operation(
            summary = "Schedule a consultation",
            description = "Schedule a consultation for a patient with a doctor",
            tags = {"consultation", "post"})
    public ResponseEntity<?> scheduleConsultation(
            @RequestBody
            @Valid
            ScheduleConsultationDTO scheduleConsultationDTO
    ) throws IntegrityValidation {
        var response = service.schelude(scheduleConsultationDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Transactional
    @Operation(
            summary = "Cancel a consultation",
            description = "Reason is required",
            tags = {"consultation", "delete"})
    public ResponseEntity<?> cancelConsultation(
            @RequestBody
            @Valid
            CancelConsultationDTO cancelConsultationDTO
    ) throws ValidationException {
        service.cancel(cancelConsultationDTO);
        return ResponseEntity.noContent().build();
    }

//        @GetMapping
//    public ResponseEntity<Page<DoctorListDTO>> getActiveDoctors(@PageableDefault(size = 2, page = 0, sort = {"name"}) Pageable page){
//        return ResponseEntity.ok(DoctorRepository.findAllByIsActiveTrue(page).map(DoctorListDTO::new));
//    }
    @GetMapping
    @Operation(
            summary = "Get all consultations",
            description = "Get all consultations",
            tags = {"consultation", "get"})
    public ResponseEntity<Page<ConsultationListDTO>> getConsultations(@PageableDefault(size = 2, page = 0) Pageable page){
        return ResponseEntity.ok(service.findAll(page).map(ConsultationListDTO::new));
    }

}
