package com.prueba.api.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", "Not Found");
        body.put("message", "El recurso solicitado no fue encontrado");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    
}
