package br.com.aulaquarkus.service;

import br.com.aulaquarkus.model.BO.Estacao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class EstacaoService {

    /**
     * Lista todas as estações cadastradas.
     * @return Lista de objetos Estacao.
     */
    public List<Estacao> listarTodos() {
        return Estacao.listAll();
    }

    /**
     * Busca uma estação pelo seu ID.
     * @param id ID da estação a ser buscada.
     * @return Objeto Estacao correspondente.
     * @throws NotFoundException se a estação não for encontrada.
     */
    public Estacao buscarPorId(long id) {
        return (Estacao) Estacao.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Estação com ID " + id + " não encontrada"));
    }

    /**
     * Cria uma  estação.
     * @param estacao Objeto Estacao com os dados da nova estação.
     * @return Objeto Estacao criado.
     * @throws IllegalArgumentException se o nome da estação for nulo ou vazio.
     */
    @Transactional
    public Estacao criar(Estacao estacao) {
        if (estacao.nome == null || estacao.nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da estação é obrigatório.");
        }
        estacao.persist();
        return estacao;
    }

    /**
     * Atualiza os dados de uma estação existente.
     * @param id ID da estação a ser atualizada.
     * @param estacaoAtualizada Objeto Estacao com os dados atualizados.
     * @return Objeto Estacao atualizado.
     */
    @Transactional
    public Estacao atualizar(Long id, Estacao estacaoAtualizada) {
        Estacao estacaoExistente = buscarPorId(id);

        estacaoExistente.nome = estacaoAtualizada.nome;
        estacaoExistente.localizacao = estacaoAtualizada.localizacao;
        estacaoExistente.temAcessibilidade = estacaoAtualizada.temAcessibilidade;

        return estacaoExistente;
    }

    /**
     * Remove uma estação pelo seu ID.
     * @param id ID da estação a ser removida.
     * @return true se a remoção foi bem-sucedida, false caso contrário.
     */
    @Transactional
    public boolean deletar(Long id) {
        return Estacao.deleteById(id);
    }
}
