package com.jcaa.cuentas.infrastructure.adapter.out.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcaa.cuentas.application.dto.AccountCreatedNotification;
import com.jcaa.cuentas.application.port.out.PublishAccountEventPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/** Publicador simulado: imprime el JSON en el log. Se activa con events.publisher=log. */
@Slf4j
@Component
@ConditionalOnProperty(name = "events.publisher", havingValue = "log", matchIfMissing = true)
public class LogAccountEventPublisher implements PublishAccountEventPort {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void publishAccountCreated(final AccountCreatedNotification notification) {
    try {
      final String json =
          objectMapper.writeValueAsString(AccountEventMessages.accountCreated(notification));
      log.info("[LogAccountEventPublisher] evento (simulado, sin Kafka): {}", json);
    } catch (final JsonProcessingException exception) {
      throw new IllegalStateException("No se pudo serializar el evento", exception);
    }
  }
}