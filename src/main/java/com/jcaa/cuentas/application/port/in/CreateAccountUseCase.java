package com.jcaa.cuentas.application.port.in;

import com.jcaa.cuentas.application.dto.CreateAccountCommand;
import com.jcaa.cuentas.domain.model.Account;

public interface CreateAccountUseCase {
  Account execute(CreateAccountCommand command);
}
