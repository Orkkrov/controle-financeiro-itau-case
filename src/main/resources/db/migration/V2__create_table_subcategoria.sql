CREATE TABLE subcategoria (
    id_subCategoria BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    id_categoria INT NOT NULL,

    CONSTRAINT fk_subCategoria_categoria
    FOREIGN KEY (id_categoria)
    REFERENCES categoria(id_categoria)
);