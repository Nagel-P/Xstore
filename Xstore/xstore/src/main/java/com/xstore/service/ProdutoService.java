package com.xstore.service;

import com.xstore.exception.ProdutoException;
import com.xstore.model.Produto;
import com.xstore.repository.ProdutoRepo;

import java.util.List;

public class ProdutoService {

    private ProdutoRepo repository =
            new ProdutoRepo();

    public List<Produto> listarTodos() {

        return repository.listarTodos();
    }

    public Produto buscarPorId(Long id) {

        Produto produto =
                repository.buscarPorId(id);

        if(produto == null) {

            throw new ProdutoException(
                    "Produto não encontrado"
            );
        }

        return produto;
    }

    public void salvar(Produto produto) {

        validarProduto(produto);

        repository.salvar(produto);
    }

    public void atualizar(Long id, Produto produto) {

        validarProduto(produto);

        buscarPorId(id);

        repository.atualizar(id, produto);
    }

    public void deletar(Long id) {

        buscarPorId(id);

        repository.deletar(id);
    }

    private void validarProduto(
            Produto produto
    ) {

        if(produto.getNome() == null
                || produto.getNome().isBlank()) {

            throw new ProdutoException(
                    "Nome obrigatório"
            );
        }

        if(produto.getPreco() <= 0) {

            throw new ProdutoException(
                    "Preço inválido"
            );
        }

        if(produto.getEstoque() < 0) {

            throw new ProdutoException(
                    "Estoque inválido"
            );
        }
    }
}