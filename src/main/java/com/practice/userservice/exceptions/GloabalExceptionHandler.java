package com.practice.userservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestControllerAdvice
public class GloabalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorResponse globleExcpetionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.warn("handle bad request for api {}: {}", request.getMethod(), request.getRequestURI(), e);
        return buildErrorResponse(response);
    }

    private ErrorResponse buildErrorResponse(HttpServletResponse response) {
        response.setStatus(500);
        var message = HttpStatus.valueOf(500).getReasonPhrase();
        return new ErrorResponse(response.getStatus(),message);
    }
}
