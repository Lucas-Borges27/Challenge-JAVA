
package br.com.aulaquarkus.model.VO;


/**
 * Classe que representa o metrô, estendendo a classe Trasnporte.
 */
public class Metro extends Trasnporte {

    /**
     * Construtor da classe Metro.
     * @param id Identificador do metrô.
     * @param nome Nome do metrô.
     * @param status Status atual do metrô.
     * @param qt_capacidade Quantidade de capacidade do metrô.
     */
    public Metro(int id, String nome, String status, int qt_capacidade) {
        super(id, nome, status, qt_capacidade);
    }

    /**
     * Atualiza o status do metrô.
     * @param novoStatus Novo status a ser definido.
     */
    public void atualizarStatus(String novoStatus) {
        setStatus(novoStatus);
        System.out.println("O status do metro " + getNome() + " foi atualizado para: " + novoStatus);
    }


}

