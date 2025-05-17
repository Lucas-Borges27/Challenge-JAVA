# Documentação dos Endpoints da API

Este documento descreve os endpoints RESTful disponíveis na aplicação, incluindo as URIs, os verbos HTTP utilizados e os códigos de status esperados nas respostas.

## Usuários (/api/usuarios)

- **GET /api/usuarios**  
  Lista todos os usuários.  
  Resposta: 200 OK

- **GET /api/usuarios/{id}**  
  Busca um usuário pelo ID.  
  Resposta: 200 OK

- **POST /api/usuarios/cadastrar**  
  Cria um novo usuário.  
  Resposta: 201 Created (sucesso), 400 Bad Request (erro)

- **PUT /api/usuarios/{id}**  
  Atualiza um usuário existente.  
  Resposta: 200 OK (sucesso), 400 Bad Request (erro)

- **DELETE /api/usuarios/{id}**  
  Remove um usuário pelo ID.  
  Resposta: 204 No Content (sucesso), 404 Not Found (não encontrado)

- **POST /api/usuarios/login**  
  Realiza o login do usuário.  
  Resposta: 200 OK (sucesso), 400 Bad Request, 401 Unauthorized, 500 Internal Server Error

## Estações (/api/estacoes)

- **GET /api/estacoes**  
  Lista todas as estações.  
  Resposta: 200 OK

- **GET /api/estacoes/{id}**  
  Busca uma estação pelo ID.  
  Resposta: 200 OK

- **POST /api/estacoes**  
  Cria uma nova estação.  
  Resposta: 201 Created (sucesso), 400 Bad Request (erro)

- **PUT /api/estacoes/{id}**  
  Atualiza uma estação existente.  
  Resposta: 200 OK (sucesso), 400 Bad Request (erro)

- **DELETE /api/estacoes/{id}**  
  Remove uma estação pelo ID.  
  Resposta: 204 No Content (sucesso), 404 Not Found (não encontrada)

## Linhas (/api/linhas)

- **GET /api/linhas**  
  Lista todas as linhas.  
  Resposta: 200 OK

- **GET /api/linhas/{id}**  
  Busca uma linha pelo ID.  
  Resposta: 200 OK

- **POST /api/linhas**  
  Cria uma nova linha.  
  Resposta: 201 Created (sucesso), 400 Bad Request (erro)

- **PUT /api/linhas/{id}**  
  Atualiza uma linha existente.  
  Resposta: 200 OK (sucesso), 400 Bad Request (erro)

- **DELETE /api/linhas/{id}**  
  Remove uma linha pelo ID.  
  Resposta: 204 No Content (sucesso), 404 Not Found (não encontrada)

---

Este documento oferece uma visão geral clara dos endpoints da API, seus métodos HTTP e os códigos de resposta esperados para cada operação.
