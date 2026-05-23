# LibraryApi

Aplicação backend desenvolvida com Spring Boot para gerenciamento de bibliotecas, permitindo o controle de catálogo, empréstimos, reservas e administração de clientes.

O projeto foi inicialmente criado como um trabalho acadêmico e atualmente está evoluindo para uma aplicação mais completa voltada para portfólio, com foco em desenvolvimento de APIs REST, autenticação JWT e implementação de regras de negócio.

---

# Funcionalidades

## Autenticação e Segurança
- Autenticação baseada em JWT
- Endpoints protegidos com Spring Security
- Fluxo de autenticação stateless
- Isolamento de dados por usuário

## Gerenciamento da Biblioteca
- Cadastro e gerenciamento de bibliotecas
- Gerenciamento de catálogo
- Gerenciamento de clientes
- Controle de empréstimos
- Sistema de reservas
- Sistema de multas baseado em atraso

## Gerenciamento de Itens
O sistema suporta múltiplos tipos de itens utilizando herança e abstração.

Tipos suportados:
- Livros
- Revistas
- Mídias
- Monografias

## Regras de Negócio
- Clientes comuns podem realizar até 1 empréstimo
- Clientes especiais podem realizar até 2 empréstimos
- Controle de disponibilidade de itens
- Controle de status de empréstimos e reservas

---

# Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Security
- JWT
- PostgreSQL
- Maven
- Hibernate / JPA
- REST API
- Arquitetura MVC

---

# Arquitetura

O projeto segue uma arquitetura em camadas:

```text
controller
 └── service
      └── repository
           └── database
```

Principais camadas:
- Controllers → gerenciamento das requisições HTTP
- Services → regras de negócio
- Repositories → acesso ao banco de dados
- Entities → modelagem de domínio
- Security → autenticação e autorização com JWT

---

# Modelagem de Domínio

A aplicação utiliza herança para representar os diferentes tipos de itens da biblioteca.

Exemplo:

```text
ItemLibrary
 ├── Book
 ├── Magazine
 ├── Media
 └── Monograph
```

Essa abordagem facilita a extensibilidade e organização do domínio.

---

# Fluxo de Autenticação

1. O usuário realiza login utilizando suas credenciais
2. A API gera um token JWT
3. O frontend armazena o token
4. O token é enviado nas requisições protegidas utilizando Bearer Authentication
5. O Spring Security valida o acesso

---

# Abordagem Multi-Tenant

Cada conta de biblioteca possui gerenciamento próprio de:
- Clientes
- Empréstimos
- Reservas
- Catálogo

O isolamento de dados é realizado através do relacionamento com o usuário autenticado.

---

# Estrutura do Projeto

```text
src/main/java
├── controller
├── service
├── repository
├── entity
├── security
├── config
└── dto
```

---

# Como Executar o Projeto

## Pré-requisitos

- Java 21
- Maven
- PostgreSQL

## Clonar o repositório

```bash
git clone https://github.com/seu-usuario/libraryapi.git
```

## Configurar variáveis de ambiente

Exemplo:

```env
DB_URL=jdbc:postgresql://localhost:5432/libraryapi
DB_USERNAME=postgres
DB_PASSWORD=postgres
JWT_SECRET=sua_chave_secreta
```

## Executar aplicação

```bash
mvn spring-boot:run
```

---

# Endpoints da API

## Autenticação

```http
POST /auth/login
POST /auth/register
```

## Itens da Biblioteca

```http
GET    /api/items
POST   /api/items
PUT    /api/items/{id}
DELETE /api/items/{id}
```

## Empréstimos

```http
POST /api/loans
PUT  /api/loans/{id}/return
```

## Reservas

```http
POST /api/reservations
```

---

# Limitações Atuais

O projeto ainda está em desenvolvimento.

Melhorias planejadas:
- Suporte a Docker
- Documentação com Swagger/OpenAPI
- Testes automatizados
- Paginação e filtros
- Renovação de empréstimos
- Histórico de empréstimos
- Sistema de fila para reservas
- Refresh Token
- Pipeline CI/CD
- Controle de permissões por roles
- Monitoramento e observabilidade

---

# Melhorias Futuras

- Configuração com Docker Compose
- Migrations com Flyway
- Testes unitários e de integração
- Documentação da API com Swagger
- Busca por ISBN, autor e título
- Filtros avançados
- Rate limiting
- Notificações por email
- Dashboard e relatórios
- Pipeline de deploy

---

# Objetivos de Aprendizado

Este projeto foi desenvolvido com o objetivo de aprofundar conhecimentos em:
- Desenvolvimento backend
- APIs REST
- Spring Security
- Autenticação JWT
- Modelagem de banco de dados
- Implementação de regras de negócio
- Arquitetura de software

---

# Frontend

O frontend está sendo desenvolvido separadamente em outro repositório.

---

# Status

Projeto em desenvolvimento ativo.

---

# Autor

Desenvolvido por Edimar
