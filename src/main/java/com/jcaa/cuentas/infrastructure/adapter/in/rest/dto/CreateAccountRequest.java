package com.jcaa.cuentas.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAccountRequest(
    @NotBlank(message = "userId es obligatorio") @Size(max = 36, message = "userId máximo 36 caracteres")
        String userId) {}
