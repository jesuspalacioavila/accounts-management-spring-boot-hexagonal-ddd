package com.jcaa.cuentas.infrastructure.adapter.in.rest;

import com.jcaa.cuentas.domain.exception.AccountAlreadyExistsException;
import com.jcaa.cuentas.domain.exception.AccountNotFoundException;
import com.jcaa.cuentas.domain.exception.InvalidAccountException;
import com.jcaa.cuentas.infrastructure.adapter.in.rest.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AccountAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleConflict(final AccountAlreadyExistsException e) {
    return build(HttpStatus.CONFLICT, e.getMessage());
  }

  @ExceptionHandler(AccountNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(final AccountNotFoundException e) {
    return build(HttpStatus.NOT_FOUND, e.getMessage());
  }

  @ExceptionHandler(InvalidAccountException.class)
  public ResponseEntity<ErrorResponse> handleInvalid(final InvalidAccountException e) {
    return build(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidation(final MethodArgumentNotValidException e) {
    final String msg =
        e.getBindingResult().getFieldErrors().stream()
            .map(fe -> fe.getDefaultMessage())
            .reduce((a, b) -> a + "; " + b)
            .orElse("Solicitud inválida");
    return build(HttpStatus.BAD_REQUEST, msg);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleUnreadable(final HttpMessageNotReadableException e) {
    return build(HttpStatus.BAD_REQUEST, "El cuerpo de la solicitud no es un JSON válido.");
  }
  
  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ErrorResponse> handleNoResource(final NoResourceFoundException e) {
    return build(HttpStatus.NOT_FOUND, "Recurso no encontrado.");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnexpected(final Exception e) {
    log.error("[GlobalExceptionHandler] error inesperado", e);
    return build(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor.");
  }

  private ResponseEntity<ErrorResponse> build(final HttpStatus status, final String message) {
    return ResponseEntity.status(status)
        .body(ErrorResponse.of(status.value(), status.getReasonPhrase(), message));
  }
}
