package com.zup.StudyGoals.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_RELATORIOS")
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Relatorio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime horaRegistro;

    @Column(nullable = false)
    private double tempoTotal;

    @Column(nullable = false)
    private double mediaTempo;

    @Column(nullable = false)
    private int totalResumos;

    @Column(nullable = false)
    private String categoriaMaisConsumida;

    @Column(nullable = false)
    private int diasParaConcluir;

    @Column(nullable = false)
    private boolean metaConcluida;

    @Column(nullable = false)
    private Long metaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(LocalDateTime horaRegistro) {
        this.horaRegistro = horaRegistro;
    }

    public double getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(double tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public double getMediaTempo() {
        return mediaTempo;
    }

    public void setMediaTempo(double mediaTempo) {
        this.mediaTempo = mediaTempo;
    }

    public int getTotalResumos() {
        return totalResumos;
    }

    public void setTotalResumos(int totalResumos) {
        this.totalResumos = totalResumos;
    }

    public String getCategoriaMaisConsumida() {
        return categoriaMaisConsumida;
    }

    public void setCategoriaMaisConsumida(String categoriaMaisConsumida) {
        this.categoriaMaisConsumida = categoriaMaisConsumida;
    }

    public int getDiasParaConcluir() {
        return diasParaConcluir;
    }

    public void setDiasParaConcluir(int diasParaConcluir) {
        this.diasParaConcluir = diasParaConcluir;
    }

    public boolean isMetaConcluida() {
        return metaConcluida;
    }

    public void setMetaConcluida(boolean metaConcluida) {
        this.metaConcluida = metaConcluida;
    }

    public Long getMetaId() {
        return metaId;
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
    }
}
