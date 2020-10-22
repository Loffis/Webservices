package se.ecutb.loffe.webservices.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestErrorHandler {

    /*
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleNotFound(ResponseStatusException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find that!");

     */

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> handleValidationErrors(ConstraintViolationException e) {
        var errors = new ArrayList<String>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<ObjectError>> handleValidationErrors(BindException e) {
        return ResponseEntity.badRequest().body(e.getAllErrors());
    }
}
