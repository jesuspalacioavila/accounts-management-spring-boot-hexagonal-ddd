package com.jcaa.cuentas.infrastructure.adapter.in.rest;

import com.jcaa.cuentas.application.dto.CreateAccountCommand;
import com.jcaa.cuentas.application.port.in.CreateAccountUseCase;
import com.jcaa.cuentas.application.port.in.GetAccountByIdUseCase;
import com.jcaa.cuentas.application.port.in.GetAccountByUserIdUseCase;
import com.jcaa.cuentas.infrastructure.adapter.in.rest.dto.AccountResponse;
import com.jcaa.cuentas.infrastructure.adapter.in.rest.dto.CreateAccountRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/** Adaptador de entrada: traduce HTTP -> casos de uso. No tiene lógica de negocio. */
@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountRestController {

  private final CreateAccountUseCase createAccountUseCase;
  private final GetAccountByIdUseCase getAccountByIdUseCase;
  private final GetAccountByUserIdUseCase getAccountByUserIdUseCase;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AccountResponse create(@Valid @RequestBody final CreateAccountRequest request) {
    return AccountResponse.from(
        createAccountUseCase.execute(new CreateAccountCommand(request.userId())));
  }

  @GetMapping("/{id}")
  public AccountResponse getById(@PathVariable final String id) {
    return AccountResponse.from(getAccountByIdUseCase.getById(id));
  }

  @GetMapping("/user/{userId}")
  public AccountResponse getByUserId(@PathVariable final String userId) {
    return AccountResponse.from(getAccountByUserIdUseCase.getByUserId(userId));
  }
}
