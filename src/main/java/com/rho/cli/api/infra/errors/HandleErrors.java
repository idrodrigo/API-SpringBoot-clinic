package com.rho.cli.api.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Array;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class HandleErrors {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errors = e.getFieldErrors().stream().map(ErrorValidationDTO::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record ErrorValidationDTO(String field, String defaultMessage) {
        public ErrorValidationDTO(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        var error = e.getMessage();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IntegrityValidation.class)
    public ResponseEntity<?> errorHandlerValidators(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> errorHandlerValidationException(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
