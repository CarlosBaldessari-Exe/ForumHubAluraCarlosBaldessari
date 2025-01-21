# 🚀 API FórumHub

## 📝 Descrição

O **FórumHub** é uma API backend desenvolvida para gerenciar tópicos de um fórum, permitindo a criação, listagem, detalhamento, atualização e exclusão (lógica) de tópicos. O projeto foi desenvolvido como parte de um desafio de estudo e foca no uso de **Java**, **Spring Boot**, e outras tecnologias do ecossistema Java para implementar as funcionalidades seguindo boas práticas de programação.

---

## 📋 Funcionalidades

### ✅ Funcionalidades Implementadas:
- **Autenticação e Autorização:**
    - Login utilizando JWT.
    - Controle de acesso baseado em perfis de usuário.
- **Gerenciamento de Tópicos:**
    - Criação de novos tópicos.
    - Listagem de todos os tópicos ativos (com paginação e ordenação).
    - Detalhamento de um tópico específico.
    - Atualização de tópicos.
    - Exclusão lógica de tópicos (soft delete).
- **Gerenciamento de Usuários:**
    - Cadastro de novos usuários.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Maven**: Gerenciamento de dependências.
- **Spring Boot**:
    - **Spring Web**: Criação de endpoints RESTful.
    - **Spring Security**: Autenticação e autorização.
    - **Spring Data JPA**: Persistência de dados.
- **Hibernate**: ORM para mapeamento de entidades.
- **Flyway**: Controle de versão do banco de dados.
- **MySQL**: Banco de dados relacional.
- **Lombok**: Redução de boilerplate no código.
- **JWT**: Para autenticação baseada em tokens.

---

## 📂 Estrutura do Projeto

O projeto segue a arquitetura em camadas, dividida nos seguintes pacotes principais:

- **configs:** Configurações gerais, incluindo CORS e segurança.
- **controller:** Controladores responsáveis por gerenciar as requisições HTTP para os endpoints.
- **domain:** Contém as entidades principais e os DTOs para entrada e saída de dados.
- **infra:** Configurações de infraestrutura, como tratamento de exceções e segurança.
- **repository:** Interfaces para comunicação com o banco de dados.
- **service:** Contém a lógica de negócios da aplicação.
- **resources/db/migration:** Scripts Flyway para gerenciamento do banco de dados.

---

## 📚 Endpoints

### Autenticação
- **POST /login**
    - Realiza a autenticação e retorna um token JWT.

### Tópicos
- **POST /topicos**
    - Cria um novo tópico.
    - **Body:**
      ```json
      {
          "titulo": "Título do Tópico",
          "mensagem": "Mensagem do tópico.",
          "autorId": 1,
          "cursoId": 2
      }
      ```

- **GET /topicos**
    - Lista todos os tópicos ativos com paginação.

- **GET /topicos/{id}**
    - Retorna os detalhes de um tópico específico.

- **PUT /topicos/{id}**
    - Atualiza os dados de um tópico.
    - **Body:**
      ```json
      {
          "id": 1,
          "titulo": "Novo Título",
          "mensagem": "Mensagem atualizada."
      }
      ```

- **DELETE /topicos/{id}**
    - Marca um tópico como inativo (exclusão lógica).

### Usuários
- **POST /usuarios/cadastro**
    - Cadastra um novo usuário.
    - **Body:**
      ```json
      {
          "nome": "João Silva",
          "email": "joao.silva@email.com",
          "senha": "123456"
      }
      ```

---

## 🔒 Segurança

- **Autenticação:** JWT é utilizado para autenticar as requisições.
- **Autorização:** Acesso a endpoints restritos com base em perfis de usuário.

---

## 🗂️ Scripts Flyway

Os scripts de migração estão localizados em `resources/db/migration` e seguem o padrão:

- **V1__create_table_curso.sql:** Criação da tabela de cursos.
- **V2__create_table_perfil.sql:** Criação da tabela de perfis.
- **V3__create_table_usuario.sql:** Criação da tabela de usuários.
- **V4__create_table_topico.sql:** Criação da tabela de tópicos.
- **V5__create_table_resposta.sql:** Criação da tabela de respostas.
- **V6__create_table_usuario_perfis.sql:** Tabela intermediária entre usuários e perfis.

---

## 📋 Como Executar o Projeto Localmente

### Pré-requisitos
- **Java 17** ou superior.
- **Maven** instalado.
- Banco de dados **MySQL** configurado.

### Passos

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/forumhub.git
   ```

2. Acesse a pasta do projeto:
   ```bash
   cd forumhub
   ```

3. Configure o banco de dados no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/forumhub
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

4. Execute as migrações do banco de dados com o Flyway:
   ```bash
   mvn flyway:migrate
   ```

5. Inicie a aplicação:
   ```bash
   mvn spring-boot:run
   ```

6. Acesse a API em `http://localhost:8080`.

---

## 🔍 Melhorias Futuras

- Adicionar documentação interativa com **Swagger**.
- Implementar testes automatizados (unitários e de integração).
- Melhorar o sistema de logs com **SLF4J** e **Logback**.
- Adicionar auditoria para exclusão lógica (data e usuário que realizou a operação).

---

## 📜 Licença

Este projeto foi desenvolvido para fins educacionais e não possui uma licença específica.
