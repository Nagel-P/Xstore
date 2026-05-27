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

CREATE TABLE IF NOT EXISTS usuario (

    email VARCHAR(255) PRIMARY KEY,

    nome VARCHAR(255) NOT NULL,

    cpf VARCHAR(20) NOT NULL,

    telefone VARCHAR(20),

    cep VARCHAR(10),

    rua VARCHAR(255),

    numero VARCHAR(20),

    bairro VARCHAR(255),

    cidade VARCHAR(255),

    uf VARCHAR(2)
);