package br.com.aulaquarkus.service;

import br.com.aulaquarkus.model.BO.Linha;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class LinhaService {

    /**
     * Lista todas as linhas cadastradas.
     * @return Lista de objetos Linha.
     */
    public List<Linha> listarTodos() {
        return Linha.listAll();
    }

    /**
     * Busca uma linha pelo seu ID.
     * @param id ID da linha a ser buscada.
     * @return Objeto Linha correspondente.
     * @throws NotFoundException se a linha não for encontrada.
     */
    public Linha buscarPorId(long id) {
        return (Linha) Linha.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Linha com ID " + id + " não encontrada"));
    }

    /**
     * Cria uma nova linha.
     * @param linha Objeto Linha com os dados da nova linha.
     * @return Objeto Linha criado.
     * @throws IllegalArgumentException se o nome da linha for nulo ou vazio.
     */
    @Transactional
    public Linha criar(Linha linha) {
        if (linha.nome == null || linha.nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da linha é obrigatório.");
        }
        linha.persist();
        return linha;
    }

    /**
     * Atualiza os dados de uma linha existente.
     * @param id ID da linha a ser atualizada.
     * @param linhaAtualizada Objeto Linha com os dados atualizados.
     * @return Objeto Linha atualizado.
     */
    @Transactional
    public Linha atualizar(Long id, Linha linhaAtualizada) {
        Linha linhaExistente = buscarPorId(id);

        linhaExistente.nome = linhaAtualizada.nome;
        linhaExistente.estacoes = linhaAtualizada.estacoes;

        return linhaExistente;
    }

    /**
     * Remove uma linha pelo seu ID.
     * @param id ID da linha a ser removida.
     * @return true se a remoção foi bem-sucedida, false caso contrário.
     */
    @Transactional
    public boolean deletar(Long id) {
        return Linha.deleteById(id);
    }
}
