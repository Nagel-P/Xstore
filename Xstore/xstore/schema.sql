CREATE TABLE IF NOT EXISTS categoria (

    id INT AUTO_INCREMENT PRIMARY KEY,

    nome VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS produto (

    id INT AUTO_INCREMENT PRIMARY KEY,

    nome VARCHAR(255) NOT NULL,

    descricao VARCHAR(500),

    preco DOUBLE,

    estoque INT,

    categoria_id INT,

    FOREIGN KEY (categoria_id)
        REFERENCES categoria(id)
);

CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    nome VARCHAR(255),
    cpf VARCHAR(20),
    telefone VARCHAR(20),
    cep VARCHAR(20),
    rua VARCHAR(255),
    numero VARCHAR(20),
    bairro VARCHAR(255),
    cidade VARCHAR(255),
    uf VARCHAR(2)
);

CREATE TABLE IF NOT EXISTS pedido (
    id IDENTITY PRIMARY KEY,
    usuario_id BIGINT,
    preco_produtos DOUBLE,
    preco_frete DOUBLE,
    preco_total DOUBLE,
    FOREIGN KEY (usuario_id)
        REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS pedido_produto (
    pedido_id BIGINT,
    produto_id BIGINT,
    PRIMARY KEY (
        pedido_id,
        produto_id
    ),
    FOREIGN KEY (pedido_id)
        REFERENCES pedido(id),
        
    FOREIGN KEY (produto_id)
        REFERENCES produto(id)
);