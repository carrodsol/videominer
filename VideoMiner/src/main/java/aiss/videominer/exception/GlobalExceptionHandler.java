package aiss.videominer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import graphql.GraphqlErrorBuilder;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler()
    public ResponseEntity<Map<String, List<String>>> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        Map<String, List<String>> res = new HashMap<>();
        res.put("message", List.of("Datos inválidos"));
        res.put("errors", errors);
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    // Error 401: Se activa cuando falta la cabecera apikey
    @ExceptionHandler({HttpClientErrorException.Unauthorized.class, MissingRequestHeaderException.class})
    @ResponseBody
    public ResponseEntity<Object> handleUnauthorized(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "No autorizado: falta la API Key en los headers");
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    // Error 400: Se activa por parámetros incorrectos (como maxVideos o maxPages mal escritos)
    @ExceptionHandler({HttpClientErrorException.BadRequest.class, MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public ResponseEntity<Object> handleBadRequest(Exception ex) {
    // Error 404
    @ExceptionHandler({HttpClientErrorException.NotFound.class, NoResourceFoundException.class})
    public ResponseEntity<Object> handleNotFound(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "El recurso solicitado no existe");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // Manejo de los NotFound personalizados
    @ExceptionHandler({ChannelNotFoundException.class, VideoNotFoundException.class, CaptionNotFoundException.class, CommentNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundTypes(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        String recurso = switch (ex.getClass().getSimpleName()) {
            case "ChannelNotFoundException" -> "Canal";
            case "VideoNotFoundException" -> "Vídeo";
            case "CaptionNotFoundException" -> "Subtítulo";
            case "CommentNotFoundException" -> "Comentario";
            default -> "";
        };
        body.put("message", recurso + " no encontrado");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // Errores 5xx: Error genérico del servidor
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Error interno del servidor");
        ex.printStackTrace();
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Manejo de los NotFound personalizados para GraphQL
    @GraphQlExceptionHandler({ChannelNotFoundException.class, VideoNotFoundException.class, CaptionNotFoundException.class, CommentNotFoundException.class})
    public GraphQLError handleNotFoundGraphQL(Exception ex, DataFetchingEnvironment env) {
        String recurso = switch (ex.getClass().getSimpleName()) {
            case "ChannelNotFoundException" -> "Canal";
            case "VideoNotFoundException" -> "Vídeo";
            case "CaptionNotFoundException" -> "Subtítulo";
            case "CommentNotFoundException" -> "Comentario";
            default -> "";
        };
        return GraphqlErrorBuilder.newError()
                .errorType(ErrorType.NOT_FOUND)
                .message(recurso+ " no encontrado")
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }
    @GraphQlExceptionHandler(MethodArgumentNotValidException.class)
    public GraphQLError handleValidationGraphQL(MethodArgumentNotValidException ex, DataFetchingEnvironment env) {
        return GraphQLError.newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message("Datos inválidos")
                .path(env.getExecutionStepInfo().getPath())
                .location(env.getField().getSourceLocation())
                .build();
    }
}