package com.jcaa.cuentas.infrastructure.adapter.out.persistence;

import com.jcaa.cuentas.application.port.out.GetAccountByIdPort;
import com.jcaa.cuentas.application.port.out.GetAccountByUserIdPort;
import com.jcaa.cuentas.application.port.out.SaveAccountPort;
import com.jcaa.cuentas.domain.enums.AccountStatus;
import com.jcaa.cuentas.domain.exception.AccountAlreadyExistsException;
import com.jcaa.cuentas.domain.model.Account;
import com.jcaa.cuentas.domain.valueobject.AccountId;
import com.jcaa.cuentas.domain.valueobject.AccountNumber;
import com.jcaa.cuentas.domain.valueobject.Balance;
import com.jcaa.cuentas.domain.valueobject.OwnerUserId;
import java.sql.Timestamp;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/** Adaptador de salida: implementa los 3 puertos de persistencia con JDBC puro. */
@Repository
@RequiredArgsConstructor
public class AccountJdbcAdapter
    implements SaveAccountPort, GetAccountByIdPort, GetAccountByUserIdPort {

  private static final String INSERT =
      "INSERT INTO accounts (id, account_number, user_id, balance, currency, status, created_at) "
          + "VALUES (?, ?, ?, ?, ?, ?, ?)";
  private static final String SELECT_BY_ID = "SELECT * FROM accounts WHERE id = ?";
  private static final String SELECT_BY_USER_ID = "SELECT * FROM accounts WHERE user_id = ?";

  private static final RowMapper<Account> MAPPER =
      (rs, rowNum) ->
          new Account(
              new AccountId(rs.getString("id")),
              new AccountNumber(rs.getString("account_number")),
              new OwnerUserId(rs.getString("user_id")),
              new Balance(rs.getBigDecimal("balance")),
              rs.getString("currency"),
              AccountStatus.valueOf(rs.getString("status")),
              rs.getTimestamp("created_at").toLocalDateTime());

  private final JdbcTemplate jdbc;

  @Override
  public Account save(final Account account) {
    try {
      jdbc.update(
          INSERT,
          account.getId().value(),
          account.getAccountNumber().value(),
          account.getUserId().value(),
          account.getBalance().value(),
          account.getCurrency(),
          account.getStatus().name(),
          Timestamp.valueOf(account.getCreatedAt()));
      return account;
    } catch (final DuplicateKeyException e) {
      // Pasa si dos requests crean la cuenta del mismo usuario a la vez (UNIQUE en user_id)
      throw AccountAlreadyExistsException.becauseUserAlreadyHasAccount(
          account.getUserId().value());
    }
  }

  @Override
  public Optional<Account> getById(final AccountId id) {
    return jdbc.query(SELECT_BY_ID, MAPPER, id.value()).stream().findFirst();
  }

  @Override
  public Optional<Account> getByUserId(final OwnerUserId userId) {
    return jdbc.query(SELECT_BY_USER_ID, MAPPER, userId.value()).stream().findFirst();
  }
}
