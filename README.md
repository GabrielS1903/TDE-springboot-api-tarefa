## README - API de Tarefas com Spring Boot

### Descrição

Este projeto é uma API RESTful para gerenciamento de tarefas, construída com Spring Boot. A API permite criar, listar, atualizar, deletar e finalizar tarefas. A aplicação utiliza PostgreSQL como banco de dados.

### Pré-requisitos

- Java 22 ou superior
- Maven
- PostgreSQL

### Configuração do Banco de Dados

Certifique-se de ter um banco de dados PostgreSQL em execução e crie um banco de dados chamado `tde-springboot`. Atualize as configurações de conexão no arquivo `application.properties`.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tde-springboot
spring.datasource.username=postgres
spring.datasource.password=masterkey
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
```

### Instalação

1. Clone o repositório:

   ```sh
   git clone https://github.com/GabrielS1903/TDE-springboot-api-tarefa
   cd api-tarefa
   ```

2. Compile e execute a aplicação:

   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

### Endpoints da API

Abaixo estão os endpoints disponíveis na API:

- **Criar uma nova tarefa:**

  ```http
  POST /tasks
  ```
  Corpo da requisição (JSON):
  ```json
  {
    "descricao": "Descrição da tarefa",
    "dataLimite": "31/12/2024"
  }
  ```

- **Obter todas as tarefas:**

  ```http
  GET /tasks
  ```

- **Obter uma tarefa por ID:**

  ```http
  GET /tasks/{id}
  ```

- **Atualizar uma tarefa por ID:**

  ```http
  PUT /tasks/{id}
  ```
  Corpo da requisição (JSON):
  ```json
  {
    "descricao": "Nova descrição da tarefa",
    "dataLimite": "31/12/2024"
  }
  ```

- **Deletar uma tarefa por ID:**

  ```http
  DELETE /tasks/{id}
  ```

- **Finalizar uma tarefa por ID:**

  ```http
  PATCH /tasks/{id}/finalizarTarefa
  ```

### Executando Testes

Para executar os testes unitários, utilize o seguinte comando Maven:

```sh
mvn test
```

### Estrutura do Projeto

- **configs:** Contém classes de configuração, como `DateConfig` para a desserialização de datas.
- **controllers:** Contém a classe `TarefaController` que define os endpoints da API.
- **dtos:** Contém classes de transferência de dados, como `TarefaDto`.
- **entities:** Contém as entidades JPA, como `TarefaEntity`.
- **repositories:** Contém os repositórios JPA, como `TarefaRepository`.
- **services:** Contém as classes de serviço, como `TarefaService`.
- **test:** Contém os testes unitários para os serviços.

### Contato

Para mais informações, entre em contato pelo email `101324@aluno.uricer.edu.br`(Gabriel) ou `101615@aluno.uricer.edu.br` (Talisson).
