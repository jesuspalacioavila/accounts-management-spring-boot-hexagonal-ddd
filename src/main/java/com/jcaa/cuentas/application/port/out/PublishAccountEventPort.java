package com.jcaa.cuentas.application.port.out;

import com.jcaa.cuentas.application.dto.AccountCreatedNotification;

public interface PublishAccountEventPort {
  void publishAccountCreated(AccountCreatedNotification notification);
}