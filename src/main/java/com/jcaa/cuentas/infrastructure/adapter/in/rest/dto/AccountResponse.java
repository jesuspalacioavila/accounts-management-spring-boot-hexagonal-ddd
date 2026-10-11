package com.jcaa.cuentas.infrastructure.adapter.in.rest.dto;

import com.jcaa.cuentas.domain.model.Account;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountResponse(
    String id,
    String accountNumber,
    String userId,
    BigDecimal balance,
    String currency,
    String status,
    LocalDateTime createdAt) {

  public static AccountResponse from(final Account a) {
    return new AccountResponse(
        a.getId().value(),
        a.getAccountNumber().value(),
        a.getUserId().value(),
        a.getBalance().value(),
        a.getCurrency(),
        a.getStatus().name(),
        a.getCreatedAt());
  }
}
