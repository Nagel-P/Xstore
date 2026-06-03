package com.xstore.service;

import com.xstore.model.Pedido;
import com.xstore.model.Produto;
import com.xstore.model.Usuario;
import com.xstore.repository.PedidoRepo;
import com.xstore.repository.ProdutoRepo;
import com.xstore.repository.UsuarioRepo;

import java.util.ArrayList;
import java.util.List;

public class PedidoService {

    private final PedidoRepo pedidoRepo =
            new PedidoRepo();

    private final UsuarioRepo usuarioRepo =
            new UsuarioRepo();

    private final ProdutoRepo produtoRepo =
            new ProdutoRepo();

    public Pedido criarPedido(
            Long usuarioId,
            List<Long> produtosIds
    ) {

        Usuario usuario =
                usuarioRepo.buscarPorId(
                        usuarioId
                );

        if (usuario == null) {

            throw new RuntimeException(
                    "Usuário não encontrado"
            );
        }

        List<Produto> produtos =
                new ArrayList<>();

        for (Long produtoId : produtosIds) {

            Produto produto =
                    produtoRepo.buscarPorId(
                            produtoId
                    );

            if (produto == null) {

                throw new RuntimeException(
                        "Produto "
                                + produtoId
                                + " não encontrado"
                );
            }

            if (produto.getEstoque() <= 0) {

                throw new RuntimeException(
                        "Produto "
                                + produto.getNome()
                                + " sem estoque"
                );
            }

            produto.setEstoque(
                    produto.getEstoque() - 1
            );

            produtoRepo.atualizar(
                    produto.getId(),
                    produto
            );

            produtos.add(produto);
        }

        Pedido pedido =
                new Pedido(
                        null,
                        usuario,
                        produtos
                );

        pedido.calcPreco();

        pedidoRepo.salvar(pedido);

        return pedido;
    }

    public List<Pedido> listarTodos() {

        return pedidoRepo.listarTodos();
    }

    public Pedido buscarPorId(
            Long id
    ) {

        Pedido pedido =
                pedidoRepo.buscarPorId(id);

        if (pedido == null) {

            throw new RuntimeException(
                    "Pedido não encontrado"
            );
        }

        return pedido;
    }
}