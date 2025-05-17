package br.com.aulaquarkus.resource;

import br.com.aulaquarkus.model.BO.Estacao;
import br.com.aulaquarkus.service.EstacaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/estacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstacaoResource {

    @Inject
    EstacaoService estacaoService;

    /**
     * Lista todas as estações.
     * @return Resposta HTTP com a lista de estações.
     */
    @GET
    public Response listarTodasEstacoes() {
        List<Estacao> estacoes = estacaoService.listarTodos();
        return Response.ok(estacoes).build();
    }

    /**
     * Busca uma estação pelo ID.
     * @param id ID da estação.
     * @return Resposta HTTP com a estação encontrada.
     */
    @GET
    @Path("/{id}")
    public Response buscarEstacaoPorId(@PathParam("id") Long id) {
        Estacao estacao = estacaoService.buscarPorId(id);
        return Response.ok(estacao).build();
    }

    /**
     * Cria uma nova estação.
     * @param estacao Objeto Estacao com os dados da nova estação.
     * @return Resposta HTTP com a estação criada ou erro.
     */
    @POST
    public Response criarEstacao(Estacao estacao) {
        try {
            Estacao novaEstacao = estacaoService.criar(estacao);
            return Response.status(Response.Status.CREATED).entity(novaEstacao).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Atualiza uma estação existente.
     * @param id ID da estação a ser atualizada.
     * @param estacao Objeto Estacao com os dados atualizados.
     * @return Resposta HTTP com a estação atualizada ou erro.
     */
    @PUT
    @Path("/{id}")
    public Response atualizarEstacao(@PathParam("id") Long id, Estacao estacao) {
        try {
            Estacao estacaoAtualizada = estacaoService.atualizar(id, estacao);
            return Response.ok(estacaoAtualizada).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Remove uma estação pelo ID.
     * @param id ID da estação a ser removida.
     * @return Resposta HTTP indicando sucesso ou falha.
     */
    @DELETE
    @Path("/{id}")
    public Response deletarEstacao(@PathParam("id") Long id) {
        if (estacaoService.deletar(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Estação com ID " + id + " não encontrada.").build();
    }
}
