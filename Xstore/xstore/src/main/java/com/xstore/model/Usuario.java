package com.xstore.model;

public class Usuario {

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private String telefone;

    private String cep;

    private String rua;

    private String numero;

    private String bairro;

    private String cidade;

    private String uf;

    public Usuario() {
    }

    public Usuario(
            Long id,
            String nome,
            String cpf,
            String email,
            String telefone,
            String cep,
            String rua,
            String numero,
            String bairro,
            String cidade,
            String uf
    ) {

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Long getId() {
        return id;
    }

    public void setId(
            Long id
    ) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(
            String nome
    ) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(
            String cpf
    ) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(
            String email
    ) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(
            String telefone
    ) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(
            String cep
    ) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(
            String rua
    ) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(
            String numero
    ) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(
            String bairro
    ) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(
            String cidade
    ) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(
            String uf
    ) {
        this.uf = uf;
    }
}