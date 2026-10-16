# Fronglos

API REST de uma mini rede social construída com Spring Boot. Usuários se cadastram, autenticam com JWT, publicam e deletam posts, seguem outros usuários e acompanham um feed com seus posts e os de quem seguem.

> Projeto de estudo com foco em **relacionamento entre entidades (JPA)**, **autenticação e autorização com JWT** e **validação de dados (Bean Validation)**.

---

## Descrição

O Fronglos permite:

- Cadastro e login de usuários, com senha armazenada em hash (BCrypt) e acesso via token JWT.
- Criação e exclusão de posts de texto (até 280 caracteres). Apenas o autor pode deletar o próprio post.
- Seguir e deixar de seguir outros usuários.
- Consultar perfis, seguidores e quem cada usuário segue.
- Consultar um feed único, paginado, com os posts do próprio usuário e dos usuários que ele segue.

### Modelo de dados

| Entidade | Descrição | Relacionamentos |
|---|---|---|
| `User` | Usuário da plataforma | 1:N com `Post`; relação N:N consigo mesmo via `Follow` |
| `Post` | Publicação de texto | N:1 com `User` (autor) |
| `Follow` | Relação seguidor → seguido, com data | N:1 com `User` (follower) e N:1 com `User` (following) |

---

## Tecnologias

- Java 25
- Spring Boot
  - Spring Web
  - Spring Security
  - Spring Data JPA
  - Spring Validation (Bean Validation)
- JWT (biblioteca `jjwt`)
- BCrypt para hash de senhas
- PostgreSQL
- SpringDoc OpenAPI (Swagger UI)
- Maven

---

## Como executar

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/fronglos.git
cd fronglos
```

### 2. Configurar as variáveis de ambiente

A chave de assinatura do JWT **não** fica no código. Defina-a no ambiente:

```bash
export JWT_SECRET="troque-por-uma-chave-longa-e-aleatoria-com-pelo-menos-32-caracteres"
export JWT_EXPIRATION_MS=3600000
```

| Variável | Descrição | Padrão |
|---|---|---|
| `JWT_SECRET` | Chave secreta usada para assinar os tokens | obrigatória |
| `JWT_EXPIRATION_MS` | Tempo de expiração do token em milissegundos | `3600000` (1 hora) |

### 3. Executar com PostgreSQL 

Crie o banco e informe as credenciais:

```bash
export DB_URL="jdbc:postgresql://localhost:5432/fronglos"
export DB_USERNAME="postgres"
export DB_PASSWORD="sua-senha"

./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres
```


### 4. Documentação interativa (Swagger)

Com a aplicação rodando, acesse:

```
http://localhost:8080/swagger-ui/index.html
```

Use o botão **Authorize** e informe o token JWT para testar as rotas protegidas.

---

## Endpoints

Todas as rotas, exceto as de `/auth`, exigem o header:

```
Authorization: Bearer <seu-token-jwt>
```

### Autenticação

| Método | Rota | Auth | Descrição | Sucesso |
|---|---|---|---|---|
| POST | `/auth/register` | Não | Cadastra um novo usuário | `201` |
| POST | `/auth/login` | Não | Autentica e retorna o JWT | `200` |

### Usuários

| Método | Rota | Auth | Descrição | Sucesso |
|---|---|---|---|---|
| GET | `/users/me` | Sim | Dados do usuário autenticado | `200` |
| GET | `/users/{id}` | Sim | Perfil público de um usuário | `200` |
| GET | `/users/{id}/posts` | Sim | Posts de um usuário (paginado) | `200` |
| GET | `/users/{id}/followers` | Sim | Seguidores de um usuário (paginado) | `200` |
| GET | `/users/{id}/following` | Sim | Quem o usuário segue (paginado) | `200` |

### Posts

| Método | Rota | Auth | Descrição | Sucesso |
|---|---|---|---|---|
| POST | `/posts` | Sim | Cria um post | `201` |
| GET | `/posts/{id}` | Sim | Consulta um post | `200` |
| DELETE | `/posts/{id}` | Sim | Deleta um post (apenas o autor) | `204` |

### Seguir usuários

| Método | Rota | Auth | Descrição | Sucesso |
|---|---|---|---|---|
| POST | `/users/{id}/follow` | Sim | Segue um usuário | `201` |
| DELETE | `/users/{id}/follow` | Sim | Deixa de seguir um usuário | `204` |

### Feed

| Método | Rota | Auth | Descrição | Sucesso |
|---|---|---|---|---|
| GET | `/feed` | Sim | Posts do usuário e de quem ele segue, do mais recente ao mais antigo (paginado) | `200` |

### Paginação

Todas as listagens aceitam:

| Parâmetro | Descrição | Regra |
|---|---|---|
| `page` | Número da página | `>= 0` (padrão `0`) |
| `size` | Itens por página | entre `1` e `50` (padrão `50`) |

Exemplo: `GET /feed?page=0&size=20`

### Exemplos de requisição

**Cadastro**

```http
POST /auth/register
Content-Type: application/json

{
  "username": "gyro_zeppeli",
  "email": "gyro@email.com",
  "password": "senha1234",
  "displayName": "Gyro Zeppeli"
}
```

**Login**

```http
POST /auth/login
Content-Type: application/json

{
  "email": "gyro@email.com",
  "password": "senha1234"
}
```

Resposta:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

**Criar post**

```http
POST /posts
Authorization: Bearer <token>
Content-Type: application/json

{
  "content": "Meu primeiro post no Fronglos!"
}
```

## Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
