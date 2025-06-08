package br.com.aulaquarkus.model.BO;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

/**
 * Classe de entidade que representa um usuário no sistema.
 * Mapeada para a tabela "USUARIOS" no banco de dados.
 */
@Entity
@Table(name = "USUARIOS")
public class Usuario extends PanacheEntity {

    /**
     * Nome do usuário. Campo obrigatório.
     */
    @Column(name = "NOME", nullable = false, length = 255)
    public String nome;

    /**
     * Preferência de acessibilidade do usuário.
     */
    @Column(name = "PREFERENCIA_ACESSIBILIDADE", length = 255)
    public String preferenciaAcessibilidade;

    /**
     * Senha do usuário. Campo obrigatório.
     */
    @Column(name = "SENHA", nullable = false, length = 255)
    public String senha;

    /**
     * Email do usuário. Campo obrigatório e único.
     */
    @Column(name = "EMAIL", nullable = false, length = 255, unique = true)
    public String email;

    // Constructors, getters, and setters are optional with Panache and public fields.
    // If using private fields, add getters and setters.
}
