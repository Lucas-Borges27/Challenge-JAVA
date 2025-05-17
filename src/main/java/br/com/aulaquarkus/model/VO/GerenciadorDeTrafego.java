package br.com.aulaquarkus.model.VO;

/**
 * Classe que representa um gerenciador de tráfego.
 */
public class GerenciadorDeTrafego {
    /**
     * Nome do gerenciador.
     */
    private String nome;

    /**
     * Número do item gerenciado.
     */
    private int numeroItem;

    /**
     * Construtor da classe GerenciadorDeTrafego.
     * @param nome Nome do gerenciador.
     * @param numeroItem Número do item gerenciado.
     */
    public GerenciadorDeTrafego(String nome, int numeroItem) {
        this.nome = nome;
        this.numeroItem = numeroItem;
    }

    /**
     * Verifica a situação do tráfego.
     */
    public void verificarSituacao() {
        System.out.println("Verificando situação do tráfego.");
    }

    /**
     * Notifica a situação do tráfego.
     */
    public void notificarSituacao() {
        System.out.println("Notificando situação de tráfego.\n");
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(int numeroItem) {
        this.numeroItem = numeroItem;
    }
}
