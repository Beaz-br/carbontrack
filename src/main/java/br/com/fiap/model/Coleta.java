package br.com.fiap.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "COLETA")
public class Coleta {

    @Id
    @Column(name = "ID_COLETA")
    private Long idColeta;

    @Column(name = "DATA_COLETA")
    private LocalDateTime dataColeta;

    @Column(name = "PESO_COLETADO")
    private Double pesoColetado;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PONTO_COLETA")
    private PontoColeta pontoColeta;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RESIDUO")
    private Residuos residuo;

    public Long getIdColeta() {
        return idColeta;
    }

    public void setIdColeta(Long idColeta) {
        this.idColeta = idColeta;
    }

    public LocalDateTime getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDateTime dataColeta) {
        this.dataColeta = dataColeta;
    }

    public Double getPesoColetado() {
        return pesoColetado;
    }

    public void setPesoColetado(Double pesoColetado) {
        this.pesoColetado = pesoColetado;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PontoColeta getPontoColeta() {
        return pontoColeta;
    }

    public void setPontoColeta(PontoColeta pontoColeta) {
        this.pontoColeta = pontoColeta;
    }

    public Residuos getResiduo() {
        return residuo;
    }

    public void setResiduo(Residuos residuo) {
        this.residuo = residuo;
    }
}
