CREATE TABLE Produto (
    idProduto INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    categoriaId INT NOT NULL,
    valor DECIMAL(15, 2) NOT NULL,
    ativo BIT DEFAULT 1 NOT NULL,
    PRIMARY KEY (idProduto),
    FOREIGN KEY (categoriaId) REFERENCES Categoria(idCategoria)
);