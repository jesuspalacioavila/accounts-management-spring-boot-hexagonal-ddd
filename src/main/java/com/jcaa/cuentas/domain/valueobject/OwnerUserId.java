package com.jcaa.cuentas.domain.valueobject;

import com.jcaa.cuentas.domain.exception.InvalidAccountException;

public record OwnerUserId(String value) {

  public OwnerUserId {
    if (value == null || value.isBlank()) {
      throw InvalidAccountException.becauseUserIdIsBlank();
    }
    value = value.trim();
  }
}
