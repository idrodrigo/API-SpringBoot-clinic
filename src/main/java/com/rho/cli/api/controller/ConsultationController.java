package com.rho.cli.api.controller;

import com.rho.cli.api.domain.consultation.CancelConsultationDTO;
import com.rho.cli.api.domain.consultation.ScheduleConsultationDTO;
import com.rho.cli.api.domain.consultation.ScheduleConsultationService;
import com.rho.cli.api.infra.errors.IntegrityValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/api/consultation")
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
}
