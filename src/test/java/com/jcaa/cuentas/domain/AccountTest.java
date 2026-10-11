package com.jcaa.cuentas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.cuentas.domain.enums.AccountStatus;
import com.jcaa.cuentas.domain.exception.InvalidAccountException;
import com.jcaa.cuentas.domain.model.Account;
import com.jcaa.cuentas.domain.valueobject.Balance;
import com.jcaa.cuentas.domain.valueobject.OwnerUserId;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class AccountTest {

  @Test
  void cuentaNuevaNaceActivaConSaldoCeroEnCop() {
    final Account account = Account.openFor(new OwnerUserId("user-1"));

    assertThat(account.getStatus()).isEqualTo(AccountStatus.ACTIVE);
    assertThat(account.getBalance().value()).isEqualByComparingTo(BigDecimal.ZERO);
    assertThat(account.getCurrency()).isEqualTo("COP");
    assertThat(account.getId().value()).isNotBlank();
    assertThat(account.getAccountNumber().value()).matches("\\d{10}");
  }

  @Test
  void userIdVacioEsInvalido() {
    assertThatThrownBy(() -> new OwnerUserId("  ")).isInstanceOf(InvalidAccountException.class);
  }

  @Test
  void saldoNegativoEsInvalido() {
    assertThatThrownBy(() -> new Balance(new BigDecimal("-1")))
        .isInstanceOf(InvalidAccountException.class);
  }
}
