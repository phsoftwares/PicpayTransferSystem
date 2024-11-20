CREATE TABLE persons (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    document VARCHAR(255) UNIQUE,
    creation_datetime TIMESTAMP NOT NULL
);

CREATE TABLE accounts (
    id BIGSERIAL PRIMARY KEY,
    input_value DECIMAL(15, 2) NOT NULL,
    output_value DECIMAL(15, 2) NOT NULL,
    initial_balance DECIMAL(15, 2) NOT NULL,
    creation_datetime TIMESTAMP NOT NULL,
    id_person BIGINT,
    FOREIGN KEY (id_person) REFERENCES persons(id)
);

CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    transaction_value DECIMAL(15, 2) NOT NULL,
    id_payee BIGINT,
    id_payer BIGINT,
    creation_datetime TIMESTAMP NOT NULL,
    FOREIGN KEY (id_payee) REFERENCES persons(id),
    FOREIGN KEY (id_payer) REFERENCES persons(id)
);
