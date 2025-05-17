package br.com.aulaquarkus.model.VO;

/**
 * Classe que representa uma resposta de erro.
 */
public class ErrorResponse {
    /**
     * Mensagem de erro.
     */
    private String mensagem;

    /**
     * Construtor da classe ErrorResponse.
     * @param mensagem Mensagem de erro.
     */
    public ErrorResponse(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * Obtém a mensagem de erro.
     * @return Mensagem de erro.
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * Define a mensagem de erro.
     * @param mensagem Mensagem de erro.
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

