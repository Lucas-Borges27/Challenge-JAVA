package br.com.aulaquarkus.model.BO;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;

/**
 * Classe de entidade que representa uma linha.
 * Mapeada para a tabela "LINHAS" no banco de dados.
 */
@Entity
@Table(name = "LINHAS")
public class Linha extends PanacheEntity {

    /**
     * Nome da linha. Campo obrigatório.
     */
    @Column(name = "NOME", nullable = false, length = 255)
    public String nome;

    /**
     * Lista de estações pertencentes à linha.
     * Operações em cascata são aplicadas.
     */
    @OneToMany(cascade = CascadeType.ALL)
    public List<Estacao> estacoes;

    /**
     * Adiciona uma estação à lista de estações da linha.
     * @param estacao Estação a ser adicionada.
     */
    public void adicionarEstacao(Estacao estacao) {
        estacoes.add(estacao);
    }

    /**
     * Exibe as estações da linha no console.
     */
    public void mostrarEstacoes() {
        System.out.println("Linha: " + nome);
        for (Estacao estacao : estacoes) {
            System.out.println("Estação: " + estacao.nome + ", Localização: " + estacao.localizacao + "\n");
        }
    }

    /**
     * Simula o trajeto passando por todas as estações da linha.
     * Exibe mensagens no console indicando o percurso.
     */
    public void simularTrechoTrajeto() {
        System.out.println("Início do trajeto na linha: " + nome);
        for (Estacao estacao : estacoes) {
            System.out.printf("Passando pela estação: %s%n", estacao.nome);
        }
        System.out.println("Fim do trajeto.");
    }
}
