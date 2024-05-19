# language: pt

Funcionalidade: Produto

  Cenario: Cadastrar produto
    Quando registrar um novo produto
    Entao o produto e registrado com sucesso
    E deve ser apresentado

  Cenario: Buscar um produto existente
    Dado que um produto ja foi cadastrado
    Quando requisitar a busca do produto
    Entao o produto e exibido com sucesso

  Cenario: Editar produto
    Dado que um produto ja foi cadastrado
    Quando requisitar a edicao do produto
    Entao o produto e editado com sucesso

  Cenario: Remover produto
    Dado que um produto ja foi cadastrado
    Quando requisitar a remocao do produto
    Entao o produto e removido com sucesso


