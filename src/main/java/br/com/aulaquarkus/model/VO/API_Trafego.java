package br.com.aulaquarkus.model.VO;

/**
 * Classe que representa uma API de tráfego.
 */
public class API_Trafego {
    /**
     * Endpoint da API.
     */
    private String endpoint;

    /**
     * Tipo da API.
     */
    private String tipo;

    /**
     * Construtor da classe API_Trafego.
     * @param endpoint Endpoint da API.
     * @param tipo Tipo da API.
     */
    public API_Trafego(String endpoint, String tipo) {
        this.endpoint = endpoint;
        this.tipo = tipo;
    }

    /**
     * Obtém dados da API.
     * @return String com mensagem de obtenção de dados.
     */
    public String obterDados() {
        return "Obtendo dados da API: " + endpoint;
    }

    /**
     * Atualiza os dados da API.
     */
    public void atualizarDados() {
        System.out.println("Atualizando dados da API.");
    }

    // Getters e Setters
    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
