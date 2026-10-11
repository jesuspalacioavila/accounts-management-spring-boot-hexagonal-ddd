package com.jcaa.cuentas.application.port.out;

import com.jcaa.cuentas.domain.model.Account;

public interface SaveAccountPort {
  Account save(Account account);
}
