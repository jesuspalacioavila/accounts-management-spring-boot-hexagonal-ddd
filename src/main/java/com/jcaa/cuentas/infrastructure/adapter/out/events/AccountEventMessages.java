package com.jcaa.cuentas.infrastructure.adapter.out.events;

import com.jcaa.cuentas.application.dto.AccountCreatedNotification;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

final class AccountEventMessages {

  private AccountEventMessages() {}

  static Map<String, Object> accountCreated(final AccountCreatedNotification notification) {
    final Map<String, Object> recipient = new LinkedHashMap<>();
    recipient.put("email", notification.email());
    recipient.put("name", notification.name());

    final Map<String, Object> data = new LinkedHashMap<>();
    data.put("userId", notification.userId());
    data.put("accountId", notification.accountId());
    data.put("accountNumber", notification.accountNumber());

    final Map<String, Object> message = new LinkedHashMap<>();
    message.put("eventId", UUID.randomUUID().toString());
    message.put("type", "ACCOUNT_CREATED");
    message.put("occurredAt", Instant.now().toString());
    message.put("recipient", recipient);
    message.put("data", data);
    return message;
  }
}