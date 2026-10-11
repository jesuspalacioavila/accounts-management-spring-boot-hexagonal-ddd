package com.jcaa.cuentas.application.port.out;

import com.jcaa.cuentas.domain.model.Account;
import com.jcaa.cuentas.domain.valueobject.OwnerUserId;
import java.util.Optional;

public interface GetAccountByUserIdPort {
  Optional<Account> getByUserId(OwnerUserId userId);
}
