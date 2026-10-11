package com.jcaa.cuentas.domain.exception;

public abstract class DomainException extends RuntimeException {
  protected DomainException(final String message) {
    super(message);
  }
}
