package com.jcaa.cuentas.application.service;

import com.jcaa.cuentas.application.port.in.GetAccountByIdUseCase;
import com.jcaa.cuentas.application.port.in.GetAccountByUserIdUseCase;
import com.jcaa.cuentas.application.port.out.GetAccountByIdPort;
import com.jcaa.cuentas.application.port.out.GetAccountByUserIdPort;
import com.jcaa.cuentas.domain.exception.AccountNotFoundException;
import com.jcaa.cuentas.domain.model.Account;
import com.jcaa.cuentas.domain.valueobject.AccountId;
import com.jcaa.cuentas.domain.valueobject.OwnerUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAccountService implements GetAccountByIdUseCase, GetAccountByUserIdUseCase {

  private final GetAccountByIdPort getAccountByIdPort;
  private final GetAccountByUserIdPort getAccountByUserIdPort;

  @Override
  public Account getById(final String accountId) {
    return getAccountByIdPort
        .getById(new AccountId(accountId))
        .orElseThrow(() -> AccountNotFoundException.becauseIdWasNotFound(accountId));
  }

  @Override
  public Account getByUserId(final String userId) {
    return getAccountByUserIdPort
        .getByUserId(new OwnerUserId(userId))
        .orElseThrow(() -> AccountNotFoundException.becauseUserWasNotFound(userId));
  }
}
