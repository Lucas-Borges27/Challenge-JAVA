package br.com.aulaquarkus.model.BO;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

/**
 * Classe de entidade que representa uma estação.
 * Mapeada para a tabela "ESTACOES" no banco de dados.
 */
@Entity
@Table(name = "ESTACOES")
public class Estacao extends PanacheEntity {

    /**
     * Nome da estação. Campo obrigatório.
     */
    @Column(name = "NOME", nullable = false, length = 255)
    public String nome;

    /**
     * Localização da estação.
     */
    @Column(name = "LOCALIZACAO", length = 255)
    public String localizacao;

    /**
     * Indica se a estação possui acessibilidade.
     */
    @Column(name = "TEM_ACESSIBILIDADE")
    public boolean temAcessibilidade;

    // Constructors, getters, and setters are optional with Panache and public fields.
    // If using private fields, add getters and setters.

    /**
     * Atualiza o status da estação.
     * Exibe uma mensagem indicando a atualização.
     */
    public void atualizarStatus() {
        System.out.println("Status da estação " + nome + " atualizado.");
    }
}
