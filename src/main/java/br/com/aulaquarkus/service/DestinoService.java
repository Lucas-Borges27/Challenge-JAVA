package br.com.aulaquarkus.service;

import br.com.aulaquarkus.model.BO.Destino;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class DestinoService {

    /**
     * Lista todos os destinos cadastrados no sistema.
     *
     * @return Lista de objetos Destino.
     */
    public List<Destino> listarTodos() {
        return Destino.listAll();
    }

    /**
     * Busca um destino pelo seu ID.
     *
     * @param id ID do destino a ser buscado.
     * @return Objeto Destino correspondente ao destino encontrado.
     * @throws NotFoundException se o destino não for encontrado.
     */
    public Destino buscarPorId(long id) {
        return (Destino) Destino.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Destino com ID " + id + " não encontrado"));
    }

    /**
     * Cria um destino no sistema.
     *
     * @param destino Objeto Destino contendo os dados do destino a ser criado.
     * @return Objeto Destino criado.
     */
    @Transactional
    public Destino criar(Destino destino, br.com.aulaquarkus.model.BO.Usuario usuario) {
        destino.usuario = usuario;
        destino.persist();
        return destino;
    }

    /**
     * Atualiza os dados de um destino existente.
     *
     * @param id      ID do destino a ser atualizado.
     * @param destino Objeto Destino com os dados atualizados.
     * @param userId  ID do usuário para associar ao destino.
     * @return Objeto Destino atualizado.
     * @throws NotFoundException se o destino não for encontrado.
     */
    @Transactional
    public Destino atualizar(Long id, Destino destino, Long userId) {
        Destino destinoExistente = (Destino) Destino.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Destino com ID " + id + " não encontrado"));

        destinoExistente.nome = destino.nome;
        destinoExistente.usuario = new br.com.aulaquarkus.model.BO.Usuario();
        destinoExistente.usuario.id = userId;

        return destinoExistente;
    }

    /**
     * Remove um destino do sistema pelo seu ID.
     *
     * @param id ID do destino a ser removido.
     * @return true se a remoção foi bem-sucedida, false caso contrário.
     */
    @Transactional
    public boolean deletar(Long id) {
        return Destino.deleteById(id);
    }

    /**
     * Lista todos os destinos associados a um usuário pelo ID do usuário.
     *
     * @param userId ID do usuário.
     * @return Lista de destinos do usuário.
     */
    public List<Destino> listarTodosPorUsuarioId(Long userId) {
        return Destino.list("usuario.id", userId);
    }
}
