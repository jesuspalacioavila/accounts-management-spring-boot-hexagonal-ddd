package com.jcaa.cuentas.application.port.in;

import com.jcaa.cuentas.domain.model.Account;

public interface GetAccountByIdUseCase {
  Account getById(String accountId);
}
