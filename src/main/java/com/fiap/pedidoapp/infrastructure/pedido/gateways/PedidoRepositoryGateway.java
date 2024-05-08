package com.fiap.pedidoapp.infrastructure.pedido.gateways;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.fiap.pedidoapp.application.cliente.usecases.BuscarClientePorCpf;
import com.fiap.pedidoapp.application.pedido.gateways.PedidoGateway;
import com.fiap.pedidoapp.application.produto.usecases.BuscarProdutoPorCodigo;
import com.fiap.pedidoapp.domain.pedido.entity.Item;
import com.fiap.pedidoapp.domain.pedido.entity.Pedido;
import com.fiap.pedidoapp.infrastructure.cliente.controllers.dto.ClienteResponseDTO;
import com.fiap.pedidoapp.infrastructure.cliente.persistence.entity.ClienteEntity;
import com.fiap.pedidoapp.infrastructure.pedido.enums.StatusPedidoEnum;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.ItemEntity;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.PedidoEntity;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.entity.StatusPedidoEntity;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.repository.PedidoRepository;
import com.fiap.pedidoapp.infrastructure.pedido.persistence.repository.StatusPedidoRepository;
import com.fiap.pedidoapp.infrastructure.produto.controllers.dto.ProdutoResponse;
import com.fiap.pedidoapp.infrastructure.produto.persistence.entity.ProdutoEntity;

public class PedidoRepositoryGateway implements PedidoGateway {

    private final BuscarClientePorCpf buscarClientePorCpf;

    private final BuscarProdutoPorCodigo buscarProdutoPorCodigo;

    private final PedidoRepository pedidoRepository;

    private final StatusPedidoRepository statusPedidoRepository;

    public static final String MSG_PEDIDO_NAO_ENCONTRADO = "Pedido não encontrado com o ID: ";

    public PedidoRepositoryGateway(BuscarClientePorCpf buscarClientePorCpf,
            BuscarProdutoPorCodigo buscarProdutoPorCodigo,
            PedidoRepository pedidoRepository,
            StatusPedidoRepository statusPedidoRepository) {
        this.buscarClientePorCpf = buscarClientePorCpf;
        this.buscarProdutoPorCodigo = buscarProdutoPorCodigo;
        this.pedidoRepository = pedidoRepository;
        this.statusPedidoRepository = statusPedidoRepository;
    }

    

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> listarPedidos() {
        List<PedidoEntity> lista = pedidoRepository.findAll();
        return lista.stream().map(Pedido::new).toList();
    }

    @Override
    public Pedido checkout(Pedido pedido) {
        PedidoEntity novoPedido = PedidoEntity.builder()
                .data(LocalDateTime.now())
                .statusPedido(StatusPedidoEntity.builder().idStatusPedido(StatusPedidoEnum.RECEBIDO.getCodigo()).build())
                .build();

        if (pedido.getCliente() != null && pedido.getCliente().getCpf() != null
                && !pedido.getCliente().getCpf().isEmpty()) {
            ClienteEntity clienteEntity = buscarClientePorCPF(pedido.getCliente().getCpf());
            novoPedido.setCliente(clienteEntity);
        }

        List<ItemEntity> itens = montarListaDeItens(pedido.getItens());
        novoPedido.setItens(itens);
        novoPedido.setPedidoNosItens();

        BigDecimal valorTotalPedido = calcularValorTotalPedido(itens);
        novoPedido.setValorTotal(valorTotalPedido);

        novoPedido = pedidoRepository.save(novoPedido);
        return new Pedido(novoPedido);
    }

    private ClienteEntity buscarClientePorCPF(String cpf) {
        ClienteResponseDTO clienteDTO = buscarClientePorCpf.buscarPorCpf(cpf);
        return clienteDTO != null ? ClienteEntity.builder()
                .idCliente(clienteDTO.getIdCliente())
                .nome(clienteDTO.getNome())
                .build() : null;
    }

    private BigDecimal calcularValorTotalPedido(List<ItemEntity> itens) {
        return itens.stream().map(item -> 
            item.getProduto().getValor().multiply(BigDecimal.valueOf(item.getQuantidade()))
        ).reduce(BigDecimal.ZERO, (totalPedido, valorTotalItem) -> totalPedido.add(valorTotalItem)).setScale(2,
                RoundingMode.HALF_UP);
    }

    private List<ItemEntity> montarListaDeItens(List<Item> itens) {
        return itens.stream().map(i -> {
            ProdutoResponse produtoDTO = buscarProdutoPorCodigo.buscarPorCodigo(i.getProduto().getIdProduto());
            ProdutoEntity produtoEntity = ProdutoEntity.builder()
                    .idProduto(produtoDTO.getIdProduto())
                    .nome(produtoDTO.getNome())
                    .valor(produtoDTO.getValor())
                    .build();

            return ItemEntity.builder()
                    .produto(produtoEntity)
                    .quantidade(i.getQuantidade())
                    .build();
        }).toList();
    }

    public Pedido buscarPorId(Integer id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_PEDIDO_NAO_ENCONTRADO + id));

        return new Pedido(pedido);
    }

    @Override
    public void atualizarStatusPedido(Integer id, String descricaoNovoStatus) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MSG_PEDIDO_NAO_ENCONTRADO + id));

        StatusPedidoEntity statusPedido = statusPedidoRepository.findByDescricao(descricaoNovoStatus)
                .orElseThrow(() -> new RuntimeException("Status do pedido inválido: " + descricaoNovoStatus));

        pedido.setStatusPedido(statusPedido);

        pedidoRepository.save(pedido);
    }

    @Override
    public void atualizarStatusPedido(Integer id, Integer idNovoStatus) {
        pedidoRepository.updateStatusByCdPedido( id, StatusPedidoEntity.builder().idStatusPedido(idNovoStatus).build());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> listarPedidosPagos() {

        return pedidoRepository.findAllByStatusPedidoIdStatusPedido(StatusPedidoEnum.PAGO.getCodigo()).stream().map( pedido -> {
            return new Pedido(pedido);
        }).toList();
    }

}
