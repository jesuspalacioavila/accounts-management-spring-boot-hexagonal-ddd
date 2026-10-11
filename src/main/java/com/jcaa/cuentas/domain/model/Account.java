package com.jcaa.cuentas.domain.model;

import com.jcaa.cuentas.domain.enums.AccountStatus;
import com.jcaa.cuentas.domain.valueobject.AccountId;
import com.jcaa.cuentas.domain.valueobject.AccountNumber;
import com.jcaa.cuentas.domain.valueobject.Balance;
import com.jcaa.cuentas.domain.valueobject.OwnerUserId;
import java.time.LocalDateTime;

public class Account {

  private static final String DEFAULT_CURRENCY = "COP";

  private final AccountId id;
  private final AccountNumber accountNumber;
  private final OwnerUserId userId;
  private final Balance balance;
  private final String currency;
  private final AccountStatus status;
  private final LocalDateTime createdAt;

  public Account(
      final AccountId id,
      final AccountNumber accountNumber,
      final OwnerUserId userId,
      final Balance balance,
      final String currency,
      final AccountStatus status,
      final LocalDateTime createdAt) {
    this.id = id;
    this.accountNumber = accountNumber;
    this.userId = userId;
    this.balance = balance;
    this.currency = currency;
    this.status = status;
    this.createdAt = createdAt;
  }

  /** Una cuenta nueva siempre nace ACTIVE, con saldo 0 y moneda COP. */
  public static Account openFor(final OwnerUserId userId) {
    return new Account(
        AccountId.generate(),
        AccountNumber.generate(),
        userId,
        Balance.zero(),
        DEFAULT_CURRENCY,
        AccountStatus.ACTIVE,
        LocalDateTime.now());
  }

  public AccountId getId() { return id; }
  public AccountNumber getAccountNumber() { return accountNumber; }
  public OwnerUserId getUserId() { return userId; }
  public Balance getBalance() { return balance; }
  public String getCurrency() { return currency; }
  public AccountStatus getStatus() { return status; }
  public LocalDateTime getCreatedAt() { return createdAt; }
}
