package com.xstore.model;

import java.util.List;

public class Pedido {

    private Integer id;

    private Usuario usuario;

    private List<Produto> produtos;

    private Double precoProdutos;

    private Double precoFrete;

    private Double precoTotal;

    public Pedido() {
    }

    public Pedido(
            Integer id,
            Usuario usuario,
            List<Produto> produtos
    ) {

        this.id = id;
        this.usuario = usuario;
        this.produtos = produtos;

        calcPreco();
    }

    public void calcPreco() {

        calcprecoProdutos();

        calcFrete();

        precoTotal =
                precoProdutos
                + precoFrete;
    }

    private void calcprecoProdutos() {

        precoProdutos = 0.0;

        for (Produto produto : produtos) {

            precoProdutos +=
                    produto.getPreco();
        }
    }

    private void calcFrete() {

    String uf =
            usuario.getUf();

    int quantidadeProdutos =
            produtos.size();

    if (uf.equalsIgnoreCase("PR")) {

        precoFrete =
                15.0 + (quantidadeProdutos * 2);

    } else if (
            uf.equalsIgnoreCase("SC")
            || uf.equalsIgnoreCase("RS")
            || uf.equalsIgnoreCase("SP")
            || uf.equalsIgnoreCase("RJ")
            || uf.equalsIgnoreCase("MG")
            
            //definição completamente arbitraria do que seria mais barato enviando com origem em curitiba
    ) {

        precoFrete =
                30.0 + (quantidadeProdutos * 4);

    } else {

        precoFrete =
                50.0 + (quantidadeProdutos * 5);
    }
}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(
            Usuario usuario
    ) {
        this.usuario = usuario;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(
            List<Produto> produtos
    ) {
        this.produtos = produtos;
    }

    public Double getprecoProdutos() {
        return precoProdutos;
    }

    public Double getprecoFrete() {
        return precoFrete;
    }

    public Double getprecoTotal() {
        return precoTotal;
    }
}