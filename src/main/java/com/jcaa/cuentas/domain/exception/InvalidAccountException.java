package com.jcaa.cuentas.domain.exception;

public class InvalidAccountException extends DomainException {
  private InvalidAccountException(final String message) {
    super(message);
  }

  public static InvalidAccountException becauseUserIdIsBlank() {
    return new InvalidAccountException("El id del usuario es obligatorio.");
  }

  public static InvalidAccountException becauseAccountIdIsBlank() {
    return new InvalidAccountException("El id de la cuenta es obligatorio.");
  }

  public static InvalidAccountException becauseAccountNumberIsInvalid() {
    return new InvalidAccountException("El número de cuenta debe tener 10 dígitos.");
  }

  public static InvalidAccountException becauseBalanceIsNegative() {
    return new InvalidAccountException("El saldo no puede ser negativo.");
  }
}
