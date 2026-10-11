package com.jcaa.cuentas.application.port.out;

import com.jcaa.cuentas.domain.model.Account;
import com.jcaa.cuentas.domain.valueobject.AccountId;
import java.util.Optional;

public interface GetAccountByIdPort {
  Optional<Account> getById(AccountId id);
}
