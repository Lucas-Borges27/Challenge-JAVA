# Projeto CCR

## Integrantes
- Lucas
- Yasmin
- Pedro

## Nome da Equipe
L.Y.P

## Nome da Solução
CCR

---

## Objetivo e Escopo
O projeto CCR tem como objetivo desenvolver uma aplicação backend em Java utilizando Quarkus, que gerencia usuários, estações e linhas de transporte. A solução oferece uma API RESTful completa para suportar as operações do front-end, incluindo autenticação, cadastro, atualização e remoção de dados, além de funcionalidades específicas para acessibilidade e simulação de trajetos.

---

## Principais Funcionalidades

- Cadastro, autenticação e gerenciamento de usuários.
- Gerenciamento de estações e linhas de transporte.
- Validações e tratamento de exceções para garantir a integridade dos dados.
- API RESTful com endpoints para todas as operações necessárias.

### Exemplo de método com lógica e código

**Método:** criar(UsuarioVO voUsuario) - UsuarioService.java  
**Descrição:** Cria um novo usuário após validar os dados recebidos e persiste no banco de dados.  
**Código:**
```java
@Transactional
public UsuarioVO criar(UsuarioVO voUsuario) {
    validarUsuario(voUsuario);
    Usuario usuario = toBO(voUsuario);
    usuario.persist();
    return toVO(usuario);
}
```

---

## Tabela de Endpoints (API RESTful)

| Recurso   | URI                    | Verbo HTTP | Códigos de Status           |
|-----------|------------------------|------------|-----------------------------|
| Usuários  | /api/usuarios          | GET        | 200 OK                      |
|           | /api/usuarios/{id}     | GET        | 200 OK                      |
|           | /api/usuarios/cadastrar| POST       | 201 Created, 400 Bad Request|
|           | /api/usuarios/{id}     | PUT        | 200 OK, 400 Bad Request     |
|           | /api/usuarios/{id}     | DELETE     | 204 No Content, 404 Not Found|
|           | /api/usuarios/login    | POST       | 200 OK, 400, 401, 500       |
| Estações  | /api/estacoes          | GET        | 200 OK                      |
|           | /api/estacoes/{id}     | GET        | 200 OK                      |
|           | /api/estacoes          | POST       | 201 Created, 400 Bad Request|
|           | /api/estacoes/{id}     | PUT        | 200 OK, 400 Bad Request     |
|           | /api/estacoes/{id}     | DELETE     | 204 No Content, 404 Not Found|
| Linhas    | /api/linhas            | GET        | 200 OK                      |
|           | /api/linhas/{id}       | GET        | 200 OK                      |
|           | /api/linhas            | POST       | 201 Created, 400 Bad Request|
|           | /api/linhas/{id}       | PUT        | 200 OK, 400 Bad Request     |
|           | /api/linhas/{id}       | DELETE     | 204 No Content, 404 Not Found|

---

## Protótipo e Jornada do Usuário

A interface do sistema é baseada em console, onde o usuário interage via terminal para realizar login, visualizar dados, e simular trajetos. A classe `LoginView` gerencia a entrada de email e senha, enquanto a classe `App` apresenta um menu interativo com opções para acessar funcionalidades como acessibilidade, dados do usuário, estações e simulação de trajetos.

---

## Modelo do Banco de Dados (MER)

O projeto utiliza entidades JPA mapeadas para tabelas do banco, como a entidade `Usuario` mapeada para a tabela "USUARIOS". Não foi encontrado um diagrama MER explícito no projeto.

---

## Diagrama de Classes (UML)

O diagrama UML está disponível no arquivo `uml/UsuarioClassesDiagram.puml`, que mostra as classes principais do sistema, suas propriedades, métodos e relacionamentos, incluindo as camadas BO, VO, DAO, Service e Controller.

---

## Procedimentos para Rodar a Aplicação

- Certifique-se de ter o Java e Maven instalados.
- Execute `./mvnw compile quarkus:dev` para iniciar o servidor em modo de desenvolvimento.
- A API estará disponível em `http://localhost:8080`.
- Utilize ferramentas como Postman para testar os endpoints conforme a tabela de endpoints.

---

## Observações Finais

Este documento cobre os principais aspectos do projeto conforme solicitado. Para dúvidas ou melhorias, entre em contato com a equipe L.Y.P.
