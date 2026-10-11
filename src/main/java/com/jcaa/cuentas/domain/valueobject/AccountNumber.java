package com.jcaa.cuentas.domain.valueobject;

import com.jcaa.cuentas.domain.exception.InvalidAccountException;
import java.security.SecureRandom;

/** Número de cuenta visible para el usuario: 10 dígitos. */
public record AccountNumber(String value) {

  private static final SecureRandom RANDOM = new SecureRandom();

  public AccountNumber {
    if (value == null || !value.trim().matches("\\d{10}")) {
      throw InvalidAccountException.becauseAccountNumberIsInvalid();
    }
    value = value.trim();
  }

  public static AccountNumber generate() {
    final long n = Math.floorMod(RANDOM.nextLong(), 10_000_000_000L);
    return new AccountNumber(String.format("%010d", n));
  }
}
