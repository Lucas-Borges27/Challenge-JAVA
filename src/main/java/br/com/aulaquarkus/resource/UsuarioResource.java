package br.com.aulaquarkus.resource;

import br.com.aulaquarkus.model.BO.LoginDTO;
import br.com.aulaquarkus.model.VO.ErrorResponse;
import br.com.aulaquarkus.model.VO.UsuarioVO;
import br.com.aulaquarkus.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("/api/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    /**
     * Lista todos os usuários.
     * @return Resposta HTTP com a lista de usuários.
     */
    @GET
    public Response listarTodosUsuarios() {
        List<UsuarioVO> usuarios = usuarioService.listarTodos();
        return Response.ok(usuarios).build();
    }

    /**
     * Busca um usuário pelo ID.
     * @param id ID do usuário.
     * @return Resposta HTTP com o usuário encontrado.
     */
    @GET
    @Path("/{id}")
    public Response buscarUsuarioPorId(@PathParam("id") Long id) {
        UsuarioVO usuario = usuarioService.buscarPorId(id);
        return Response.ok(usuario).build();
    }

    /**
     * Cria um usuário.
     * @param usuario Objeto UsuarioVO com os dados do usuário.
     * @return Resposta HTTP com o usuário criado ou erro.
     */
    @POST
    @Path("/cadastrar")
    public Response criarUsuario(UsuarioVO usuario) {
        try {
            UsuarioVO novoUsuario = usuarioService.criar(usuario);
            return Response.status(Response.Status.CREATED).entity(novoUsuario).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Atualiza um usuário existente.
     * @param id ID do usuário a ser atualizado.
     * @param usuario Objeto UsuarioVO com os dados atualizados.
     * @return Resposta HTTP com o usuário atualizado ou erro.
     */
    @PUT
    @Path("/{id}")
    public Response atualizarUsuario(@PathParam("id") Long id, UsuarioVO usuario) {
        try {
            UsuarioVO usuarioAtualizado = usuarioService.atualizar(id, usuario);
            return Response.ok(usuarioAtualizado).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Remove um usuário pelo ID.
     * @param id ID do usuário a ser removido.
     * @return Resposta HTTP indicando sucesso ou falha.
     */
    @DELETE
    @Path("/{id}")
    public Response deletarUsuario(@PathParam("id") Long id) {
        if (usuarioService.deletar(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Usuário com ID " + id + " não encontrado.").build();
    }

    /**
     * Realiza o login do usuário.
     * @param loginDTO Objeto LoginDTO com email e senha.
     * @return Resposta HTTP com o usuário autenticado ou erro.
     */
    @POST
    @Path("/login")
    public Response login(LoginDTO loginDTO) {
        try {
            UsuarioVO usuario = usuarioService.login(loginDTO.getEmail(), loginDTO.getSenha());
            String cookieHeader = "userId=" + usuario.getId() +
                    "; Path=/" +
                    "; HttpOnly" +
                    "; Secure" +  // Enable Secure flag for HTTPS
                    "; SameSite=None";
            return Response.ok(usuario)
                    .header("Set-Cookie", cookieHeader)
                    .build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage()))
                    .build();
        } catch (SecurityException e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new
                            ErrorResponse(e.getMessage()))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Erro interno do servidor"))
                    .build();
        }

    }
 
}
