package com.highlevel.interview.exception;

import com.highlevel.interview.dto.ErrorResponseDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  // Body validation: @Valid on @RequestBody DTOs
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDto> handleBodyValidation(MethodArgumentNotValidException ex) {
    String message =
        ex.getBindingResult().getFieldErrors().stream()
            .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
            .collect(Collectors.joining(", "));
    log.warn("Request body validation failed: {}", message);
    return build(HttpStatus.BAD_REQUEST, message);
  }

  // Method parameter validation: @Validated on controller + constraints on @RequestHeader /
  // @PathVariable / @RequestParam
  @ExceptionHandler(HandlerMethodValidationException.class)
  public ResponseEntity<ErrorResponseDto> handleMethodValidation(
      HandlerMethodValidationException ex) {
    String paramErrors =
        ex.getParameterValidationResults().stream()
            .flatMap(r -> r.getResolvableErrors().stream())
            .map(MessageSourceResolvable::getDefaultMessage)
            .filter(m -> m != null && !m.isBlank())
            .collect(Collectors.joining(", "));
    String crossErrors =
        ex.getCrossParameterValidationResults().stream()
            .map(MessageSourceResolvable::getDefaultMessage)
            .filter(m -> m != null && !m.isBlank())
            .collect(Collectors.joining(", "));
    String message =
        java.util.stream.Stream.of(paramErrors, crossErrors)
            .filter(s -> !s.isBlank())
            .collect(Collectors.joining(", "));
    if (message.isBlank()) {
      message = "Request validation failed";
    }
    log.warn("Method parameter validation failed: {}", message);
    return build(HttpStatus.BAD_REQUEST, message);
  }

  // Bean Validation triggered programmatically (jakarta.validation)
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponseDto> handleConstraintViolation(
      ConstraintViolationException ex) {
    String message =
        ex.getConstraintViolations().stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining(", "));
    log.warn("Constraint violation: {}", message);
    return build(HttpStatus.BAD_REQUEST, message);
  }

  // Malformed JSON / missing body
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponseDto> handleNotReadable(HttpMessageNotReadableException ex) {
    log.warn("Malformed request body: {}", ex.getMessage());
    return build(HttpStatus.BAD_REQUEST, "Malformed or missing request body");
  }

  // Missing required header (e.g. X-USER-UUID)
  @ExceptionHandler(MissingRequestHeaderException.class)
  public ResponseEntity<ErrorResponseDto> handleMissingHeader(MissingRequestHeaderException ex) {
    String message = "Missing required header: " + ex.getHeaderName();
    log.warn(message);
    return build(HttpStatus.BAD_REQUEST, message);
  }

  // Catch-all for anything else thrown by controllers/services
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> handleAll(Exception ex) {
    log.error("Unhandled exception", ex);
    String message = ex.getMessage() != null ? ex.getMessage() : "Internal server error";
    return build(HttpStatus.INTERNAL_SERVER_ERROR, message);
  }

  private ResponseEntity<ErrorResponseDto> build(HttpStatus status, String message) {
    ErrorResponseDto body =
        ErrorResponseDto.builder()
            .status(status.value())
            .error(status.getReasonPhrase())
            .message(message)
            .timestamp(Instant.now().toString())
            .build();
    return ResponseEntity.status(status).body(body);
  }
}
