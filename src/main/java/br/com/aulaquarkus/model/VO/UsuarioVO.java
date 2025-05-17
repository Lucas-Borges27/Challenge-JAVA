package br.com.aulaquarkus.model.VO;

/**
 * Classe de objeto de valor (VO) que representa um usuário.
 * Contém os dados do usuário para transferência entre camadas.
 */
public class UsuarioVO {
    /**
     * Identificador único do usuário.
     */
    private int id;

    /**
     * Nome do usuário.
     */
    private String nome;

    /**
     * Preferência de acessibilidade do usuário.
     */
    private String preferenciaAcessibilidade;

    /**
     * Destino do usuário.
     */
    private String destino;

    /**
     * Senha do usuário.
     */
    private String senha;

    /**
     * Email do usuário.
     */
    private String email;

    /**
     * Construtor da classe UsuarioVO.
     * @param nome Nome do usuário.
     * @param email Email do usuário.
     * @param senha Senha do usuário.
     */
    public UsuarioVO(String nome, String email, String senha) {
        this.nome = nome;
        this.email  = email;
        this.senha = senha;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPreferenciaAcessibilidade() {
        return preferenciaAcessibilidade;
    }

    public void setPreferenciaAcessibilidade(String preferenciaAcessibilidade) {
        this.preferenciaAcessibilidade = preferenciaAcessibilidade;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna uma representação em string do objeto UsuarioVO.
     * @return String formatada com ID, nome e senha do usuário.
     */
    @Override
    public String toString() {
        return String.format(
                "Usuario {\n" +
                        "  ID: %d\n" +
                        "  Nome: %s\n" +
                        "  Senha: %s\n" +
                        "}",
                id, nome, senha
        );
    }
}




