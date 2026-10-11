package com.jcaa.cuentas.domain.valueobject;

import com.jcaa.cuentas.domain.exception.InvalidAccountException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public record Balance(BigDecimal value) {

  public Balance {
    if (value == null || value.signum() < 0) {
      throw InvalidAccountException.becauseBalanceIsNegative();
    }
    value = value.setScale(2, RoundingMode.HALF_UP);
  }

  public static Balance zero() {
    return new Balance(BigDecimal.ZERO);
  }
}
