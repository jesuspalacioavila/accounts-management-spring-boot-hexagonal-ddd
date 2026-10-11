package com.jcaa.cuentas.domain.exception;

public class AccountAlreadyExistsException extends DomainException {
  private AccountAlreadyExistsException(final String message) {
    super(message);
  }

  public static AccountAlreadyExistsException becauseUserAlreadyHasAccount(final String userId) {
    return new AccountAlreadyExistsException(
        "El usuario '" + userId + "' ya tiene una cuenta.");
  }
}
