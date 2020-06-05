package com.healthyfoody.config;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.healthyfoody.exception.ApiError;
import com.healthyfoody.exception.ResourceNotFoundException;

@RestControllerAdvice
public class ControllerAdvisor {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiError handleConstraintViolation(ConstraintViolationException e, WebRequest request) {

        return new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), e.toString(), request);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiError handleResourceNotFound(ResourceNotFoundException e, WebRequest request) {
        return new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), e.toString(), request);
    }
}
