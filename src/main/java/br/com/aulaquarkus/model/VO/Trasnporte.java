package br.com.aulaquarkus.model.VO;

/**
 * Classe abstrata que representa um transporte.
 */
abstract class Trasnporte {
    /**
     * Identificador do transporte.
     */
    private int id;

    /**
     * Nome do transporte.
     */
    private String nome;

    /**
     * Status atual do transporte.
     */
    private String status;

    /**
     * Quantidade de capacidade do transporte.
     */
    private int qt_capacidade;

    /**
     * Construtor da classe Trasnporte.
     * @param id Identificador do transporte.
     * @param nome Nome do transporte.
     * @param status Status atual do transporte.
     * @param qt_capacidade Quantidade de capacidade do transporte.
     */
    public Trasnporte(int id, String nome, String status, int qt_capacidade) {
        this.id = id;
        this.nome = nome;
        this.qt_capacidade = qt_capacidade;
        this.status = status;
    }

    /**
     * Método para atualizar o status do transporte.
     * Deve ser implementado pelas subclasses.
     */
    void atualizarStatus(){};

    // Getters e Setters
    int getId() {
        return id;
    }

     void setId(int id) {
        this.id = id;
    }

    String getNome() {
        return nome;
    }

    void setNome(String nome) {
        this.nome = nome;
    }

   String getStatus() {
        return status;
    }

     void setStatus(String status) {
        this.status = status;
    }

    int getQt_capacidade() {
        return qt_capacidade;
    }

    void setQt_capacidade(int qt_capacidade) {
        this.qt_capacidade = qt_capacidade;
    }
}
