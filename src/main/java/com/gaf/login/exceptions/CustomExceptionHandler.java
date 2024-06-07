package com.gaf.login.exceptions;

import com.gaf.login.domain.dto.error.ErrorMessageDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        List<ErrorMessageDTO> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorMessageDTO(err.getDefaultMessage(), err.getField()))
                .collect(Collectors.toList());

        var customError = new CustomError<>(request.getDescription(false), errors, HttpStatus.BAD_REQUEST);
        return new ResponseEntity(customError, customError.getStatus());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String message = ex.getMostSpecificCause().getMessage();
        var customError = new CustomError<>(request.getDescription(false), message, HttpStatus.BAD_REQUEST);
        return new ResponseEntity(customError, customError.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex, WebRequest request) {
        var customError = new CustomError<>(request.getDescription(false), ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity(customError, customError.getStatus());
    }

}
