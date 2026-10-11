package com.jcaa.cuentas.infrastructure.adapter.in.rest.dto;

import java.time.LocalDateTime;

public record ErrorResponse(int status, String error, String message, LocalDateTime timestamp) {
  public static ErrorResponse of(final int status, final String error, final String message) {
    return new ErrorResponse(status, error, message, LocalDateTime.now());
  }
}
