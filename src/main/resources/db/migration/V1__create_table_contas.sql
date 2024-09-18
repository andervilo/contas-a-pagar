CREATE TABLE contas (
    id BIGSERIAL PRIMARY KEY,
    data_vencimento DATE NOT NULL,
    data_pagamento TIMESTAMP,
    valor DECIMAL(10, 2) NOT NULL,
    descricao TEXT,
    situacao VARCHAR(50) NOT NULL
);