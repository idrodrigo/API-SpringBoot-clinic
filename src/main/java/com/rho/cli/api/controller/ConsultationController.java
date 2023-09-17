package com.rho.cli.api.controller;

import com.rho.cli.api.domain.consultation.ScheduleConsultationDTO;
import com.rho.cli.api.domain.consultation.ScheduleConsultationService;
import com.rho.cli.api.domain.consultation.scheduleDetailsConsultationDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/api/consultation")
public class ConsultationController {
    @Autowired
    private ScheduleConsultationService scheduleConsultationService;
    @PostMapping
    @Transactional
    public ResponseEntity<?> scheduleConsultation(
            @RequestBody
            @Valid
            ScheduleConsultationDTO scheduleConsultationDTO
    ) {
        scheduleConsultationService.schelude(scheduleConsultationDTO);
        return ResponseEntity.ok(new scheduleDetailsConsultationDTO(
                null, null, null, null
        ));
    }
}
