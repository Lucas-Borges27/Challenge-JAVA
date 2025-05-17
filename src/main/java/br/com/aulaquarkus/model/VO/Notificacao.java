package br.com.aulaquarkus.model.VO;

/**
 * Classe que representa uma notificação.
 */
public class Notificacao {
    /**
     * Mensagem da notificação.
     */
    private String mensagem;

    /**
     * Prioridade da notificação.
     */
    private String prioridade;

    /**
     * Construtor da classe Notificacao.
     * @param mensagem Mensagem da notificação.
     * @param prioridade Prioridade da notificação.
     */
    public Notificacao(String mensagem, String prioridade) {
        this.mensagem = mensagem;
        this.prioridade = prioridade;
    }

    /**
     * Envia a notificação.
     */
    public void enviarNotificacao() {
        System.out.println("Notificação enviada: " + mensagem + " - Prioridade: " + prioridade);
    }

    // Getters e Setters
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }
}
