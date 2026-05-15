package com.xstore.model;

public class Produto {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer estoque;
    private Long categoriaId;

    public Produto() {
    }

    public Produto(Long id,
                    String nome,
                    String descricao,
                    Double preco,
                    Integer estoque,
                    Long categoriaId) {

        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.categoriaId = categoriaId;
    }

    public boolean disponivel() {
        return estoque > 0;
    }

    public String classificacaoPreco() {

        if (preco >= 5000) {
            return "ELITE";
        }

        if (preco >= 2000) {
            return "PREMIUM";
        }

        return "PADRAO";
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getClassificacao() {
    return classificacaoPreco();
}

public boolean getDisponivel() {
    return disponivel();
}
}