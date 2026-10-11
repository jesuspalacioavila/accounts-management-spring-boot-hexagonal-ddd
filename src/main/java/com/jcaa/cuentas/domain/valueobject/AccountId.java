package com.jcaa.cuentas.domain.valueobject;

import com.jcaa.cuentas.domain.exception.InvalidAccountException;
import java.util.UUID;

public record AccountId(String value) {

  public AccountId {
    if (value == null || value.isBlank()) {
      throw InvalidAccountException.becauseAccountIdIsBlank();
    }
    value = value.trim();
  }

  public static AccountId generate() {
    return new AccountId(UUID.randomUUID().toString());
  }
}
