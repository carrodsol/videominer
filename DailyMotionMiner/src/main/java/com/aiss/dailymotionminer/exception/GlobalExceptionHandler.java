package com.aiss.dailymotionminer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import java.util.HashMap;
import java.util.Map;

// Gestiona errores que provienen directamente del controlador, no de los servicios.
@ControllerAdvice
public class GlobalExceptionHandler {

    // Error 404
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    @ResponseBody
    public ResponseEntity<Object> handleNotFound(HttpClientErrorException.NotFound ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "El recurso solicitado no existe en DailyMotion");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // Error 400
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    @ResponseBody
    public ResponseEntity<Object> handleBadRequest(HttpClientErrorException.BadRequest ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Parámetros incorrecto de búsqueda");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Errores 5xx
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Error interno del servidor");
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
