package com.expedientesclinicos.controller;

import com.expedientesclinicos.exception.AccessDeniedException;
import com.expedientesclinicos.exception.DomainException;
import com.expedientesclinicos.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        return buildResponse(ex.getMessage(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({DomainException.class})
    public ResponseEntity<ApiError> handleDomain(DomainException ex, HttpServletRequest request) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        String message = errors.isEmpty() ? "Solicitud inválida" : errors.toString();
        return buildResponse(message, HttpStatus.BAD_REQUEST, request);
    }

    private ResponseEntity<ApiError> buildResponse(String message, HttpStatus status, HttpServletRequest request) {
        ApiError error = new ApiError(Instant.now().toString(), status.value(), status.getReasonPhrase(), message, request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    public record ApiError(String timestamp,
                           int status,
                           String error,
                           String message,
                           String path) {
    }
}
