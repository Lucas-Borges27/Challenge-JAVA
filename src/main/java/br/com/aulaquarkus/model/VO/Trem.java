package br.com.aulaquarkus.model.VO;

/**
 * Classe que representa o trem, estendendo a classe Trasnporte.
 */
public class Trem extends Trasnporte {

    /**
     * Construtor da classe Trem.
     * @param id Identificador do trem.
     * @param nome Nome do trem.
     * @param status Status atual do trem.
     * @param qt_capacidade Quantidade de capacidade do trem.
     */
    public Trem(int id, String nome, String status, int qt_capacidade) {
        super(id, nome, status, qt_capacidade);
    }

    /**
     * Atualiza o status do trem.
     * @param novoStatus Novo status a ser definido.
     */
    public void atualizarStatus(String novoStatus) {
            setStatus(novoStatus);
            System.out.println("O status do trem " + getNome() + " foi atualizado para: " + novoStatus);
        }


    }

