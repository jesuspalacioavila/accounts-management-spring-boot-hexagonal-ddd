CREATE TABLE IF NOT EXISTS accounts (
  id             VARCHAR(36)   NOT NULL PRIMARY KEY,
  account_number VARCHAR(10)   NOT NULL UNIQUE,
  user_id        VARCHAR(36)   NOT NULL UNIQUE,
  balance        DECIMAL(19,2) NOT NULL DEFAULT 0,
  currency       VARCHAR(3)    NOT NULL DEFAULT 'COP',
  status         VARCHAR(20)   NOT NULL DEFAULT 'ACTIVE',
  created_at     DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT chk_accounts_balance CHECK (balance >= 0),
  CONSTRAINT chk_accounts_status CHECK (status IN ('ACTIVE','BLOCKED','CLOSED'))
);
