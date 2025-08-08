# ToDoApp - Backend

[![Java Version](https://img.shields.io/badge/Java-17-blue.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.5-green.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Docker](https://img.shields.io/badge/Docker-conteinerizado-blue.svg)](https://www.docker.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14.1-orange.svg)](https://www.postgresql.org/)

Uma aplicação Backend robusta para gerenciamento de tarefas, desenvolvida com **Spring Boot** e **Spring Data JPA**, utilizando **PostgreSQL** como banco de dados e **Docker Compose** para orquestração de contêineres.

O frontend correspondente em **React** pode ser encontrado no repositório: [ToDoApp-Frontend](https://github.com/apolinario0x21/toDoApp_Frontend)

## 🚀 Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.4.5** e **Spring Data JPA**
- **PostgreSQL** (Produção) / **H2** (Desenvolvimento)
- **Lombok**
- **Docker Compose**
- **JUnit 5** e **Mockito** (para testes)
- **Springdoc OpenAPI** (Swagger UI)

## ⚙️ Configuração e Execução do Ambiente

### Pré-requisitos
Certifique-se de ter as seguintes ferramentas instaladas em sua máquina:
- **Java Development Kit (JDK) 17**
- **Maven**
- **Docker** e **Docker Compose**

### 📦 Executando o Projeto com Docker

O projeto é conteinerizado usando Docker Compose. As configurações de banco de dados são gerenciadas de forma segura por meio de um arquivo de variáveis de ambiente (`.env`).

1.  **Configurar Credenciais (Segurança)**
    Na raiz do projeto, crie um arquivo chamado `.env` para armazenar as credenciais do banco de dados. **Adicione `.env` ao seu `.gitignore` para não expor as credenciais**.

    `.env`
    ```
    POSTGRES_USER=user
    POSTGRES_PASSWORD=password
    POSTGRES_DB=database
    ```

2.  **Executar em Modo de Produção (com PostgreSQL)**
    O `docker-compose.yml` usará as credenciais do seu arquivo `.env` para iniciar o backend e o banco de dados.

    ```bash
    # Para construir a imagem e iniciar os contêineres pela primeira vez
    docker-compose up -d --build
    ```
    O backend estará disponível em `http://localhost:8080`.


3. **Executar em Modo de Desenvolvimento (com H2)**
    Para rodar com o banco de dados H2 em memória, use o arquivo de configuração de desenvolvimento.

    ```bash
    # Pare os contêineres atuais
    docker-compose down

    # Construa e inicie o ambiente de desenvolvimento
    docker-compose -f docker-compose.dev.yml up --build
    ```

### 🧪 Testes Automatizados
O projeto conta com uma suíte de testes de unidade implementada com JUnit e Mockito.

1.  **Executar todos os testes**
    ```bash
    mvn test
    ```
    Este comando executa todos os testes localizados na pasta `src/test`.

## ✅ Funcionalidades
- CRUD completo de tarefas.
- Validações de dados.
- API documentada com Swagger UI.
- Ambiente de produção com PostgreSQL e desenvolvimento com H2.
- Testes de unidade com JUnit e Mockito.

## 📚 Acessos Úteis
- **Swagger UI (Documentação da API)**: `http://localhost:8080/swagger-ui.html`
- **H2 Console (somente no modo dev)**: `http://localhost:8080/h2-console`
    - JDBC URL: `jdbc:h2:mem:todoapp_db`
    - Usuário: `sa`
    - Senha: (vazio)

## 📂 Estrutura do Projeto

```
toDoApp/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── apolinario0x21/toDoApp/
│   │   │       ├── config/
│   │   │       │   ├── SwaggerConfig.java
│   │   │       │   └── WebConfig.java
│   │   │       ├── controller/
│   │   │       │   └── TaskController.java
│   │   │       ├── dto/
│   │   │       │   ├── TaskRequestDTO.java
│   │   │       │   ├── TaskResponseDTO.java
│   │   │       │   ├── TaskStatusUpdateRequestDTO.java
│   │   │       │   └── UpdateTaskTitleDTO.java
│   │   │       ├── exceptions/
│   │   │       │   ├── GlobalExceptionHandler.java
│   │   │       │   └── TaskNotFoundException.java
│   │   │       ├── model/
│   │   │       │   └── Task.java
│   │   │       ├── repository/
│   │   │       │   └── TaskRepository.java
│   │   │       ├── service/
│   │   │       │   └── TaskService.java
│   │   │       └── ToDoAppApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       └── application-prod.properties
│   └── test/
│       └── java/
│           └── apolinario0x21/toDoApp/
│               ├── service/
│               │   └── TaskServiceTest.java
│               └── ToDoAppApplicationTests.java
└── 
```

## 🔮 Roadmap
- Autenticação com JWT.
- Sistema de filtros e ordenação de tarefas.

## 🤝 Contribuição
Se você quiser contribuir, siga as boas práticas de desenvolvimento:
- Faça um fork do repositório.
- Crie uma nova branch para sua feature (`git checkout -b feature/minha-feature`).
- Faça seus commits.
- Abra um Pull Request.