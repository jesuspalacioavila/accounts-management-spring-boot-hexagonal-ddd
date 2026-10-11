package com.jcaa.cuentas.application.service;

import com.jcaa.cuentas.application.dto.CreateAccountCommand;
import com.jcaa.cuentas.application.port.in.CreateAccountUseCase;
import com.jcaa.cuentas.application.port.out.GetAccountByUserIdPort;
import com.jcaa.cuentas.application.port.out.SaveAccountPort;
import com.jcaa.cuentas.domain.exception.AccountAlreadyExistsException;
import com.jcaa.cuentas.domain.model.Account;
import com.jcaa.cuentas.domain.valueobject.OwnerUserId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateAccountService implements CreateAccountUseCase {

  private final SaveAccountPort saveAccountPort;
  private final GetAccountByUserIdPort getAccountByUserIdPort;

  @Override
  public Account execute(final CreateAccountCommand command) {
    final OwnerUserId userId = new OwnerUserId(command.userId());
    getAccountByUserIdPort
        .getByUserId(userId)
        .ifPresent(
            existing -> {
              throw AccountAlreadyExistsException.becauseUserAlreadyHasAccount(userId.value());
            });
    final Account saved = saveAccountPort.save(Account.openFor(userId));
    log.info("[CreateAccountService] cuenta creada id={} userId={}", saved.getId().value(), userId.value());
    return saved;
  }
}
