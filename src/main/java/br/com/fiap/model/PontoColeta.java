package br.com.fiap.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "PONTO_COLETA")
public class PontoColeta {

    @Id
    @Column(name = "ID_PONTO")
    private Long idPonto;

    @Column(name = "LOCALIZACAO")
    private String localizacao;

    @Column(name = "CAPACIDADE_MAXIMA")
    private Double capacidadeMaxima;

    @Column(name = "NIVEL_ATUAL")
    private Double nivelAtual;


    @JsonIgnore
    @OneToMany(mappedBy = "pontoColeta", fetch = FetchType.LAZY)
    private List<Coleta> coletas;

    @JsonIgnore
    @OneToMany(mappedBy = "pontoColeta", fetch = FetchType.LAZY)
    private List<Alerta> alertas;


    public Long getIdPonto() {
        return idPonto;
    }

    public void setIdPonto(Long idPonto) {
        this.idPonto = idPonto;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Double getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(Double capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public Double getNivelAtual() {
        return nivelAtual;
    }

    public void setNivelAtual(Double nivelAtual) {
        this.nivelAtual = nivelAtual;
    }

    public List<Coleta> getColetas() {
        return coletas;
    }

    public void setColetas(List<Coleta> coletas) {
        this.coletas = coletas;
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }
}
