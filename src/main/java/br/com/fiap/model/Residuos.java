package br.com.fiap.model;

import jakarta.persistence.*;

@Entity
@Table(name = "RESIDUOS")

public class Residuos {

    @Id
    @Column(name = "ID_RESIDUO")
    private Long id;

    @Column(name = "TIPO_RESIDUO")
    private String tipoResiduo;

    @Column(name = "DESCRICAO")
    private String descricao;

    public Residuos() {}

    public Residuos(Long id, String tipoResiduo, String descricao) {
        this.id = id;
        this.tipoResiduo = tipoResiduo;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(String tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
