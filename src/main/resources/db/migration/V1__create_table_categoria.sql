CREATE TABLE IF NOT EXISTS categoria(
    id_categoria INT(11) AUTO_INCREMENT primary key,
    nome varchar (100) not null UNIQUE
);