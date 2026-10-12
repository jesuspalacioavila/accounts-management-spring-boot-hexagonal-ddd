package com.jcaa.cuentas.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAccountRequest(
    @NotBlank(message = "userId es obligatorio")
        @Size(max = 36, message = "userId máximo 36 caracteres")
        String userId,
    @NotBlank(message = "name es obligatorio") String name,
    @NotBlank(message = "email es obligatorio") @Email(message = "email inválido") String email) {}