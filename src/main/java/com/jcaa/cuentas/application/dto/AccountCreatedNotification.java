package com.jcaa.cuentas.application.dto;

public record AccountCreatedNotification(
    String accountId, String accountNumber, String userId, String email, String name) {}