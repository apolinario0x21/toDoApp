# ToDoApp - Backend


[![Java Version](https://img.shields.io/badge/Java-17-blue.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4-green.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Docker](https://img.shields.io/badge/Docker-20.10.7-blue.svg)](https://www.docker.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14.1-orange.svg)](https://www.postgresql.org/)
[![React](https://img.shields.io/badge/React-17.0.2-lightblue.svg)](https://reactjs.org/)


Uma aplicação Backend para gerenciamento de tarefas, com backend em **Spring Boot**, usando **PostgreSQL** como banco de dados e **Docker** para conteinerização.

Obs.: Frontend em **React** encontra-se no repositório: [ToDoApp-Frontend](https://github.com/apolinario0x21/toDoApp_Frontend)

---

## Sobre o Projeto
ToDoApp é uma aplicação de gerenciamento de tarefas completa que permite aos usuários gerenciar suas atividades.
O backend foi desenvolvido seguindo as melhores práticas do Java e Spring Boot.
---
## 🚀 Tecnologias Utilizadas
- Java 17
- Spring Boot 3.4
- Spring Data JPA
- PostgreSQL / H2 (dev)
- Lombok
- Docker
- Springdoc OpenAPI (Swagger UI)

---

## ⚙️ Pré-requisitos

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em sua máquina:

- **Java Development Kit (JDK) 17** ou superior
- **Maven** (para build do projeto Java)
- **Node.js** e **npm** ou **yarn** (para o frontend React, se você for executá-lo separadamente)
- **Docker** (para conteinerização)
- **Docker Compose** (opcional, mas recomendado para facilitar a execução com PostgreSQL)

---


## 🛠️ Configuração do Ambiente

### Configuração do Backend (Spring Boot)

1.  **Clone o repositório:**
    ```bash
    git clone git@github.com:apolinario0x21/toDoApp.git
    cd todoApp/ 
    ```

2.  **Configurações do Banco de Dados:**
    - **PostgreSQL (Produção):** As configurações de conexão estão definidas nas variáveis de ambiente ou no arquivo `application.properties`. Certifique-se de que o banco de dados PostgreSQL esteja rodando e acessível.
    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/todoapp_db
    spring.datasource.username=myuser
    spring.datasource.password=mypassword
    spring.jpa.hibernate.ddl-auto=update # Ou outra estratégia de DDL
    ```
    - **H2 (Desenvolvimento):** O H2 é configurado para rodar em memória e é ativado pelo perfil dev. Não requer configuração adicional. O console do H2 estará disponível em http://localhost:8080/h2-console durante a execução com o perfil de desenvolvimento.


3. **Build do Projeto (Maven):**
    ```bash
    mvn clean install
    ```
   Este comando irá compilar o projeto, executar os testes e gerar o arquivo .jar executável.


4.  **Execução do Backend (sem Docker):**
    ```bash
    mvn spring-boot:run -Dspring-boot.run.profiles=dev # Para ambiente de desenvolvimento com H2
    mvn spring-boot:run # Para ambiente de produção (certifique-se das configurações do PostgreSQL)
    ```
    O perfil `dev` ativa o banco de dados H2 e outras configurações específicas para desenvolvimento.    

---

## 📦 Executando o projeto com Docker

> Certifique-se de ter o Docker instalado em sua máquina.

### Build da imagem:

```bash
docker build -t todo-app .
```

### Iniciar os containers:

```bash
docker-compose up -d
```
---

## 🧪 Acessos úteis
Swagger UI: `http://localhost:8080/swagger-ui.html`

H2 Console (modo dev): `http://localhost:8080/h2-console`

    JDBC URL: jdbc:h2:mem:todoapp
    Usuário: sa
    Senha: (vazio)

PostgreSQL (prod):

    URL: jdbc:postgresql://localhost:5432/todoapp_db
    Usuário: myuser
    Senha: mypassword

---

## 📂 Estrutura do Projeto (Backend)
- src/
    - main/
        - java/
            - apolinario0x21/toDoApp/
                - `config/` - Configurações
                - `controller/` - Endpoints API
                - `dto/` - Data Transfer Objects
                - `exceptions/` - Handler de erros
                - `model/` - Entidades
                - `repository/` - Repositórios JPA
                - `service/` - Lógica de negócio
                - `ToDoAppApplication.java`
        - `resources/` - application.properties
    - `test/` - Testes

---

## ✅ Funcionalidades
- CRUD completo de tarefas.
- Atualização de título e status
- Validações com Jakarta Bean Validation
- API documentada com Swagger
- Perfil `dev` com H2 e `prod` com PostgreSQL
- Logging com SLF4J
- Monitoramento com Spring Boot Actuator

## 🔮 Roadmap (Futuras Implementações)
### Backend
- Ordenação de tarefas por data ou status (mais recente, mais antigo, pendente)
- Filtros avançados:

  - Tarefas concluídas/não concluídas
  - Busca por palavra-chave no título ou ID

- Sistema de logs para tarefas removidas/alteradas
- Autenticação JWT para usuários/admin
- Testes automatizados com JUnit/Mockito

### Infraestrutura
- SLF4J para logging
- Spring Boot Actuator para monitoramento
- Integração com UptimeRobot para monitoramento

### Testes
- JUnit/Mockito

### Segurança
- Spring Security para autenticação e autorização com JWT
- Proteção contra CSRF, XSS e SQL Injection
---

## 📚 Documentação da API - Tasks
### Endpoints

| Método | Endpoint                | Descrição                          |
|--------|-------------------------|------------------------------------|
| POST   | `/api/tasks`            | Cria uma nova tarefa               |
| GET    | `/api/tasks`            | Lista todas as tarefas             |
| PUT    | `/api/tasks/{id}`       | Atualiza o título de uma tarefa    |
| PATCH  | `/api/tasks/{id}`       | Atualiza o status de uma tarefa    |
| DELETE | `/api/tasks/{id}`       | Remove uma tarefa                  |

---


## 🤝 Contribuição
Se você quiser contribuir com este projeto, siga estas etapas:

- Faça um fork do repositório.
- Crie uma branch para sua feature `git checkout -b feature/nova-funcionalidade`.
- Faça seus commits `git commit -m 'Adiciona nova funcionalidade'`.
- Faça um push para a branch `git push origin feature/nova-funcionalidade`.
- Abra um Pull Request.