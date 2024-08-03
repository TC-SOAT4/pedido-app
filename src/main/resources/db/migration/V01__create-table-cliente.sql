CREATE TABLE Cliente (
    idCliente INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    email VARCHAR(255) NULL,
    ativo BIT DEFAULT 1 NOT NULL,
    endereco VARCHAR(255) NULL,
    telefone VARCHAR(20) NULL,
    UNIQUE (cpf),
    UNIQUE (email),
    PRIMARY KEY (idCliente)
);
