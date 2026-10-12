package com.jcaa.cuentas.application.dto;

public record CreateAccountCommand(String userId, String name, String email) {}