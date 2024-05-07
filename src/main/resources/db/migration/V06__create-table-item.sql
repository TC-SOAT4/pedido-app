CREATE TABLE Item (
    idItem INT NOT NULL AUTO_INCREMENT,
    produtoId INT NOT NULL,
    pedidoId INT NOT NULL,
    quantidade INT NOT NULL,
    PRIMARY KEY (idItem),
    FOREIGN KEY (produtoId) REFERENCES Produto(idProduto),
    FOREIGN KEY (pedidoId) REFERENCES Pedido(idPedido)
);