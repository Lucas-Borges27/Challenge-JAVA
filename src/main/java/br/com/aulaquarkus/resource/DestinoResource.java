package br.com.aulaquarkus.resource;

import br.com.aulaquarkus.model.BO.Destino;
import br.com.aulaquarkus.service.DestinoService;
import br.com.aulaquarkus.service.UsuarioService;
import io.vertx.core.http.Cookie;
import io.vertx.ext.web.RoutingContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.WebApplicationException;

import java.util.List;

@Path("/api/destinos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DestinoResource {

    @Inject
    DestinoService destinoService;

    @Inject
    UsuarioService usuarioService;

    @Context
    RoutingContext routingContext;

    /**
     * Lista todos os destinos do usuário logado.
     * @return Resposta HTTP com a lista de destinos.
     */
    @GET
    public List<Destino> listarTodosDestinos() {
        io.vertx.core.http.Cookie userIdCookie = null;
        if (routingContext != null && routingContext.request() != null) {
            userIdCookie = routingContext.request().getCookie("userId");
        }
        if (userIdCookie == null) {
            System.err.println("Usuário não autenticado: cookie userId não encontrado.");
            throw new WebApplicationException("Usuário não autenticado.", Response.Status.UNAUTHORIZED);
        }
        Long userId;
        try {
            userId = Long.parseLong(userIdCookie.getValue());
            System.out.println("UserId cookie value: " + userId);
        } catch (NumberFormatException e) {
            System.err.println("ID de usuário inválido no cookie: " + userIdCookie.getValue());
            throw new WebApplicationException("ID de usuário inválido no cookie.", Response.Status.BAD_REQUEST);
        }
        if (usuarioService.buscarPorId(userId) == null) {
            System.err.println("Usuário não encontrado para ID: " + userId);
            throw new WebApplicationException("Usuário não encontrado.", Response.Status.UNAUTHORIZED);
        }
        System.out.println("Usuário logado: ID " + userId);
        return destinoService.listarTodosPorUsuarioId(userId);
    }

    /**
     * Lista todos os destinos de todos os usuários.
     * @return Resposta HTTP com a lista de todos os destinos.
     */
    @GET
    @Path("/todas")
    public List<Destino> listarTodosDestinosTodosUsuarios() {
        return destinoService.listarTodos();
    }

    /**
     * Busca um destino pelo ID.
     * @param id ID do destino.
     * @return Resposta HTTP com o destino encontrado.
     */
    @GET
    @Path("/{id}")
    public Response buscarDestinoPorId(@PathParam("id") Long id) {
        Destino destino = destinoService.buscarPorId(id);
        return Response.ok(destino).build();
    }

    /**
     * Cria um destino.
     * @param destino Objeto Destino com os dados do destino.
     * @return Resposta HTTP com o destino criado ou erro.
     */
    @POST
    public Response criarDestino(Destino destino) {
        if (destino == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("O objeto Destino é obrigatório.").build();
        }
        if (destino.nome == null || destino.nome.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("O campo 'nome' é obrigatório.").build();
        }
        io.vertx.core.http.Cookie userIdCookie = null;
        if (routingContext != null && routingContext.request() != null) {
            userIdCookie = routingContext.request().getCookie("userId");
            if (userIdCookie != null) {
                System.out.println("Cookie userId found: " + userIdCookie.getValue());
            } else {
                System.err.println("Cookie userId is null.");
                return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário não autenticado.").build();
            }
        } else {
            System.err.println("Request or headers are null, cannot get cookie.");
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário não autenticado.").build();
        }
        Long userId;
        try {
            userId = Long.parseLong(userIdCookie.getValue());
            System.out.println("UserId cookie value: " + userId);
        } catch (NumberFormatException e) {
            System.err.println("ID de usuário inválido no cookie: " + userIdCookie.getValue());
            return Response.status(Response.Status.BAD_REQUEST).entity("ID de usuário inválido no cookie.").build();
        }
        br.com.aulaquarkus.model.VO.UsuarioVO usuarioLogadoVO = usuarioService.buscarPorId(userId);
        if (usuarioLogadoVO == null) {
            System.err.println("Usuário não encontrado para ID: " + userId);
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário não encontrado.").build();
        }
        br.com.aulaquarkus.model.BO.Usuario usuarioLogado = new br.com.aulaquarkus.model.BO.Usuario();
        usuarioLogado.id = (long) usuarioLogadoVO.getId();
        Destino novoDestino = destinoService.criar(destino, usuarioLogado);
        return Response.status(Response.Status.CREATED).entity(novoDestino).build();
    }

    /**
     * Atualiza um destino existente.
     * @param id ID do destino a ser atualizado.
     * @param destino Objeto Destino com os dados atualizados.
     * @return Resposta HTTP com o destino atualizado ou erro.
     */
    @PUT
    @Path("/{id}")
    public Response atualizarDestino(@PathParam("id") Long id, @CookieParam("userId") Long userId, Destino destino) {
        if (userId == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário não autenticado").build();
        }
        Destino destinoAtualizado = destinoService.atualizar(id, destino, userId);
        return Response.ok(destinoAtualizado).build();
    }

    /**
     * Remove um destino pelo ID.
     * @param id ID do destino a ser removido.
     * @return Resposta HTTP indicando sucesso ou falha.
     */
    @DELETE
    @Path("/{id}")
    public Response deletarDestino(@PathParam("id") Long id) {
        io.vertx.core.http.Cookie userIdCookie = null;
        if (routingContext != null && routingContext.request() != null) {
            userIdCookie = routingContext.request().getCookie("userId");
        }
        if (userIdCookie == null) {
            System.err.println("Usuário não autenticado: cookie userId não encontrado.");
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário não autenticado.").build();
        }
        Long userId;
        try {
            userId = Long.parseLong(userIdCookie.getValue());
            System.out.println("UserId cookie value: " + userId);
        } catch (NumberFormatException e) {
            System.err.println("ID de usuário inválido no cookie: " + userIdCookie.getValue());
            return Response.status(Response.Status.BAD_REQUEST).entity("ID de usuário inválido no cookie.").build();
        }
        if (usuarioService.buscarPorId(userId) == null) {
            System.err.println("Usuário não encontrado para ID: " + userId);
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário não encontrado.").build();
        }
        if (destinoService.deletar(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Destino com ID " + id + " não encontrado.").build();
    }
}
