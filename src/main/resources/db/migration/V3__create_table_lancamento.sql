CREATE TABLE lancamento (
    id_lancamento BIGINT AUTO_INCREMENT PRIMARY KEY,
    valor DECIMAL(10,2) NOT NULL,
    data DATE NOT NULL,
    comentario VARCHAR(255),
    id_subCategoria BIGINT NOT NULL,

    CONSTRAINT fk_lancamento_subCategoria
    FOREIGN KEY (id_subCategoria)
    REFERENCES subCategoria(id_subCategoria)
);