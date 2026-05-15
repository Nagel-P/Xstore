package com.xstore.dto;

import com.xstore.model.Produto;

public class ProdutoDto {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer estoque;
    private Long categoriaId;
    private String classificacao;
    private boolean disponivel;

    public ProdutoDto(Produto produto) {

        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco();
        this.estoque = produto.getEstoque();
        this.categoriaId = produto.getCategoriaId();

        this.classificacao = produto.getClassificacao();
        this.disponivel = produto.getDisponivel();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
}