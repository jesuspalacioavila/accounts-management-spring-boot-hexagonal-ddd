package com.jcaa.cuentas.domain.exception;

public class AccountNotFoundException extends DomainException {
  private AccountNotFoundException(final String message) {
    super(message);
  }

  public static AccountNotFoundException becauseIdWasNotFound(final String id) {
    return new AccountNotFoundException("No existe una cuenta con id '" + id + "'.");
  }

  public static AccountNotFoundException becauseUserWasNotFound(final String userId) {
    return new AccountNotFoundException("No existe una cuenta para el usuario '" + userId + "'.");
  }
}
