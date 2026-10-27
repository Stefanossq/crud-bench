-- Tabela de clientes
CREATE TABLE IF NOT EXISTS clientes (
    id            BIGSERIAL PRIMARY KEY,
    nome          VARCHAR(150) NOT NULL,
    email         VARCHAR(150) NOT NULL UNIQUE,
    telefone      VARCHAR(30),
    cidade        VARCHAR(100),
    estado        CHAR(2),
    data_cadastro TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Índices (vamos usar depois para o cenário "com índice")
-- Por enquanto NÃO criamos — queremos medir "sem índice" primeiro.
-- Deixei os comandos comentados para você ativar quando chegar a hora:
--
-- CREATE INDEX idx_clientes_nome   ON clientes (nome);
-- CREATE INDEX idx_clientes_email  ON clientes (email);
-- CREATE INDEX idx_clientes_cidade ON clientes (cidade);