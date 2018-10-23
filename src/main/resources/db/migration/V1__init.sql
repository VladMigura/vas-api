CREATE TABLE participant (
  id BIGSERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  middle_name VARCHAR(255) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ,
  deleted_at TIMESTAMPTZ
);

CREATE TABLE payment (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL REFERENCES participant(id),
  year BIGINT NOT NULL,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ,
  deleted_at TIMESTAMPTZ
);

CREATE TABLE admin_table (
  id BIGSERIAL PRIMARY KEY,
  admin_name VARCHAR(255) NOT NULL,
  hash_password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ,
  deleted_at TIMESTAMPTZ
);

CREATE TABLE token (
  id BIGSERIAL PRIMARY KEY,
  value VARCHAR(255) NOT NULL,
  admin_id BIGINT NOT NULL REFERENCES admin_table(id),
  created_at TIMESTAMPTZ NOT NULL,
  updated_at TIMESTAMPTZ,
  deleted_at TIMESTAMPTZ
);