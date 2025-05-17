package br.com.aulaquarkus.resource;

import br.com.aulaquarkus.model.BO.Linha;
import br.com.aulaquarkus.service.LinhaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/linhas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LinhaResource {

    @Inject
    LinhaService linhaService;

    /**
     * Lista todas as linhas.
     * @return Resposta HTTP com a lista de linhas.
     */
    @GET
    public Response listarTodasLinhas() {
        List<Linha> linhas = linhaService.listarTodos();
        return Response.ok(linhas).build();
    }

    /**
     * Busca uma linha pelo ID.
     * @param id ID da linha.
     * @return Resposta HTTP com a linha encontrada.
     */
    @GET
    @Path("/{id}")
    public Response buscarLinhaPorId(@PathParam("id") Long id) {
        Linha linha = linhaService.buscarPorId(id);
        return Response.ok(linha).build();
    }

    /**
     * Cria uma nova linha.
     * @param linha Objeto Linha com os dados da nova linha.
     * @return Resposta HTTP com a linha criada ou erro.
     */
    @POST
    public Response criarLinha(Linha linha) {
        try {
            Linha novaLinha = linhaService.criar(linha);
            return Response.status(Response.Status.CREATED).entity(novaLinha).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Atualiza uma linha existente.
     * @param id ID da linha a ser atualizada.
     * @param linha Objeto Linha com os dados atualizados.
     * @return Resposta HTTP com a linha atualizada ou erro.
     */
    @PUT
    @Path("/{id}")
    public Response atualizarLinha(@PathParam("id") Long id, Linha linha) {
        try {
            Linha linhaAtualizada = linhaService.atualizar(id, linha);
            return Response.ok(linhaAtualizada).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Remove uma linha pelo ID.
     * @param id ID da linha a ser removida.
     * @return Resposta HTTP indicando sucesso ou falha.
     */
    @DELETE
    @Path("/{id}")
    public Response deletarLinha(@PathParam("id") Long id) {
        if (linhaService.deletar(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Linha com ID " + id + " não encontrada.").build();
    }
}
