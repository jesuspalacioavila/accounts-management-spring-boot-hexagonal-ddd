package com.jcaa.cuentas.application.port.in;

import com.jcaa.cuentas.domain.model.Account;

public interface GetAccountByUserIdUseCase {
  Account getByUserId(String userId);
}
