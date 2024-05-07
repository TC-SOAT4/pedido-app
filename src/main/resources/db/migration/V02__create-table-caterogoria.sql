CREATE TABLE Categoria (
    idCategoria INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    ativo BIT DEFAULT 1 NOT NULL,
    PRIMARY KEY (idCategoria)
);