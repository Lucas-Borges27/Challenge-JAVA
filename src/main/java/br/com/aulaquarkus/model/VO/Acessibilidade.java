package br.com.aulaquarkus.model.VO;

/**
 * Classe que representa as opções de acessibilidade.
 */
public class Acessibilidade {
    /**
     * Descrição das opções de acessibilidade.
     */
    private String descricao;

    /**
     * Indica se suporta elevador.
     */
    private boolean suportaElevador;

    /**
     * Indica se suporta rampa.
     */
    private boolean suportaRampa;

    /**
     * Construtor da classe Acessibilidade.
     * @param descricao Descrição das opções.
     * @param suportaElevador Indica suporte a elevador.
     * @param suportaRampa Indica suporte a rampa.
     */
    public Acessibilidade(String descricao, boolean suportaElevador, boolean suportaRampa) {
        this.descricao = descricao;
        this.suportaElevador = suportaElevador;
        this.suportaRampa = suportaRampa;
    }

    /**
     * Ativa o suporte para rampa.
     */
    public void ativarSuporteRampa() {
        System.out.println("Suporte para rampa ativado.");
    }

    /**
     * Ativa o suporte para elevador.
     */
    public void ativarSuporteElevador() {
        System.out.println("Suporte para elevador ativado.");
    }

    /**
     * Mostra as opções de acessibilidade.
     */
    public void mostrarOpcoes() {
        System.out.println("Opções de acessibilidade: " + descricao);
    }

    // Getters e Setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isSuportaElevador() {
        return suportaElevador;
    }

    public void setSuportaElevador(boolean suportaElevador) {
        this.suportaElevador = suportaElevador;
    }

    public boolean isSuportaRampa() {
        return suportaRampa;
    }

    public void setSuportaRampa(boolean suportaRampa) {
        this.suportaRampa = suportaRampa;
    }
}
