# Controle Financeiro API

API REST desenvolvida como **case técnico** para gerenciamento de lançamentos financeiros, permitindo o cadastro e consulta de receitas, despesas e balanço financeiro por período e categoria.

---

# Tecnologias Utilizadas

* **Java 8**
* **Spring Boot 2.3.1**
* **MySQL 8.4**
* **Spring Data JPA**
* **Docker**
* **Docker Compose**

---

# Funcionalidades

A API permite:

* Cadastro de **categorias**
* Cadastro de **subcategorias**
* Cadastro de **lançamentos financeiros**
* Consulta de **balanço financeiro por período**
* Filtro por **categoria**

O balanço retorna:

* **Receita**: soma de todos os valores positivos
* **Despesa**: soma de todos os valores negativos
* **Saldo**: receita - despesa

---

# Estrutura do Projeto

```
controle-financeiro-api
│
├─ src/
├─ Dockerfile
├─ docker-compose.yml
├─ pom.xml
└─ README.md
```

---

# Pré-requisitos

Antes de executar o projeto é necessário ter instalado:

* Docker
* Docker Compose

---

# Como Executar o Projeto

A aplicação foi containerizada utilizando **Docker** e **Docker Compose**, permitindo subir toda a infraestrutura (API + Banco de dados) com um único comando.

### 1 - Clonar o repositório

```
git clone <url-do-repositorio>
```

### 2 - Acessar a pasta do projeto

```
cd controle-financeiro-api
```

### 3 - Gerar o build da aplicação

Caso esteja utilizando uma IDE como IntelliJ, execute o lifecycle **package** do Maven para gerar o arquivo `.jar`.

Isso criará o arquivo:

```
target/seguro-api-0.0.1-SNAPSHOT.jar
```

### 4 - Subir os containers

Execute o comando:

```
docker compose up --build
```

Esse comando irá:

* Construir a imagem da aplicação
* Subir um container da API
* Subir um container do MySQL
* Conectar automaticamente a aplicação ao banco

---

# Serviços Disponíveis

Após subir os containers:

API:

```
http://localhost:8080
```

Banco de dados MySQL:

```
localhost:3306
```

Credenciais do banco:

```
Database: db_controle_financeiro
User: root
Password: root
```

---

# Parar os Containers

Para parar os containers:

```
docker compose down
```

Para remover containers e volumes do banco:

```
docker compose down -v
```

---

# Observações

* O banco de dados é criado automaticamente na inicialização do container MySQL.
* As tabelas são geradas automaticamente pelo Hibernate.

---

# Autor

Desenvolvido como **case técnico** para avaliação de backend.

---
