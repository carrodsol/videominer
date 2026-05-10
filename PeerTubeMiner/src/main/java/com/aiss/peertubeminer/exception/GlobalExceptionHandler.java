package com.aiss.peertubeminer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Gestión genérica de errores
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, List<String>>> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        Map<String, List<String>> res = new HashMap<>();
        res.put("errors", errors);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    // Error 404: Se activa si el recurso no existe en PeerTube o si la URL es errónea

    // Para que se encargue de las dos
    @ExceptionHandler({HttpClientErrorException.NotFound.class, NoResourceFoundException.class})
    @ResponseBody
    public ResponseEntity<Object> handleNotFound(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "El recurso solicitado no existe en PeerTube");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // Error 401: Se activa cuando falta la cabecera apikey
    @ExceptionHandler({HttpClientErrorException.Unauthorized.class, MissingRequestHeaderException.class})
    public ResponseEntity<Object> handleUnauthorized(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "No autorizado: falta la API Key en los headers");
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    // Error 400: Se activa por parámetros incorrectos (como maxVideos o maxPages mal escritos)
    @ExceptionHandler({HttpClientErrorException.BadRequest.class, MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public ResponseEntity<Object> handleBadRequest(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Parámetros incorrectos de búsqueda");
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Errores 5xx: Error genérico del servidor
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Error interno del servidor");
        ex.printStackTrace();
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}