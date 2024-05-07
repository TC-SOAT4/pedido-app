-- PEDIDO PRONTO
INSERT INTO Pedido (idPedido, clienteId, statusPedidoId, data) VALUES (1, 1, 3, current_timestamp);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (1, 1, 1);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (1, 4, 1);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (1, 8, 1);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (1, 10, 1);

-- PEDIDO EM PREPARACAO
INSERT INTO Pedido (idPedido, clienteId, statusPedidoId, data) VALUES (2, 2, 2, current_timestamp);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (2, 2, 2);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (2, 5, 2);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (2, 8, 1);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (2, 9, 1);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (2, 10, 1);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (2, 11, 1);

-- PEDIDO RECEBIDO
INSERT INTO Pedido (idPedido, clienteId, statusPedidoId, data) VALUES (3, 1, 1, current_timestamp);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (3, 3, 1);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (3, 6, 1);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (3, 8, 1);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (3, 11, 1);


-- PEDIDO FINALIZADO
INSERT INTO Pedido (idPedido, clienteId, statusPedidoId, data) VALUES (4, 2, 4, current_timestamp);
INSERT INTO Item (pedidoId,produtoId,quantidade) VALUES (4, 11, 1);