package br.com.aulaquarkus.model.BO;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "DESTINOS")
public class Destino extends PanacheEntity  {
    /**
     * Nome do destino. Campo obrigatório.
     */
    @Column(name = "DESTINO", nullable = false, length = 255)
    public String nome;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    public Usuario usuario;
}


