package com.rho.cli.api.domain.consultation.validation;

import com.rho.cli.api.domain.consultation.CancelConsultationDTO;
import com.rho.cli.api.domain.consultation.ConsultationRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CancelAnticipationTime implements CancelConsultValidator{
    @Autowired
    private ConsultationRepository repository;

    @Override
    public void validate(CancelConsultationDTO cancelConsultationDTO) {
        var now = LocalDateTime.now();
        var consult = repository.findById(cancelConsultationDTO.id()).get();
        var difference = Duration.between(now, consult.getDate());
        if(difference.toHours() < 24){
            throw new ValidationException("The consultation must be canceled at least 24 hours in advance");
        }

    }
}
