package com.healthyfoody.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    Instant timestamp;

    Integer status;

    String error;

    String message;

    Object details;

    String path;

    public ApiError(HttpStatus status, String message, Object details, WebRequest request) {
        this.timestamp = Instant.now();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.details = details;
        this.path = ((ServletWebRequest)request).getRequest().getRequestURI().toString();
    }

}
