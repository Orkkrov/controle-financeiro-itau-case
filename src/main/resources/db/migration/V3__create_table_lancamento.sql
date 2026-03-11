CREATE TABLE lancamento (
    id_lancamento BIGINT AUTO_INCREMENT PRIMARY KEY,
    valor DECIMAL(10,2) NOT NULL,
    data DATE NOT NULL,
    comentario VARCHAR(255),
    id_subcategoria BIGINT NOT NULL,

    CONSTRAINT fk_lancamento_subcategoria
    FOREIGN KEY (id_subcategoria)
    REFERENCES subcategoria(id_subcategoria)
);