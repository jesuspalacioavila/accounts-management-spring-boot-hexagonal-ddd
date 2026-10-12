package com.jcaa.cuentas.infrastructure.adapter.out.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcaa.cuentas.application.dto.AccountCreatedNotification;
import com.jcaa.cuentas.application.port.out.PublishAccountEventPort;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/** Publicador real: envía el JSON a Kafka. Se activa con events.publisher=kafka. */
@Slf4j
@Component
@ConditionalOnProperty(name = "events.publisher", havingValue = "kafka")
public class KafkaAccountEventPublisher implements PublishAccountEventPort {

  private final KafkaProducer<String, String> producer;
  private final String topic;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public KafkaAccountEventPublisher(
      final KafkaProducer<String, String> producer,
      @Value("${kafka.topic.accounts}") final String topic) {
    this.producer = producer;
    this.topic = topic;
  }

  @Override
  public void publishAccountCreated(final AccountCreatedNotification notification) {
    final String json = toJson(notification);
    producer.send(
        new ProducerRecord<>(topic, notification.userId(), json),
        (metadata, exception) -> {
          if (exception != null) {
            log.error(
                "[KafkaAccountEventPublisher] no se pudo publicar ACCOUNT_CREATED en {}: {}",
                topic,
                exception.toString());
          } else {
            log.info(
                "[KafkaAccountEventPublisher] publicado ACCOUNT_CREATED topic={} partition={} offset={}",
                metadata.topic(),
                metadata.partition(),
                metadata.offset());
          }
        });
  }

  private String toJson(final AccountCreatedNotification notification) {
    try {
      return objectMapper.writeValueAsString(AccountEventMessages.accountCreated(notification));
    } catch (final JsonProcessingException exception) {
      throw new IllegalStateException("No se pudo serializar el evento", exception);
    }
  }
}