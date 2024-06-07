package com.gaf.login.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class CustomError<T> {
    private final String description;
    private final T error;
    private final HttpStatus status;
    private final LocalDateTime timestamp;

    public CustomError(String description, T error, HttpStatus status) {
        this.description = description;
        this.error = error;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }
}
