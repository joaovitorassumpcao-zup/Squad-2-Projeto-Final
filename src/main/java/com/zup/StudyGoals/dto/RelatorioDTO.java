package com.zup.StudyGoals.dto;

import com.zup.StudyGoals.domain.Relatorio;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

public class RelatorioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    double tempoTotal;

    double mediaTempo;

    int totalResumos;

    String categoriaMaisConsumida;

    int diasParaConcluir;

    boolean metaConcluida;

    public RelatorioDTO(double tempoTotal, double mediaTempo, int totalResumos, String categoriaMaisConsumida, int diasParaConcluir, boolean metaConcluida) {
        this.tempoTotal = tempoTotal;
        this.mediaTempo = mediaTempo;
        this.totalResumos = totalResumos;
        this.categoriaMaisConsumida = categoriaMaisConsumida;
        this.diasParaConcluir = diasParaConcluir;
        this.metaConcluida = metaConcluida;
    }

    public RelatorioDTO (Relatorio relatorio) {
        this.tempoTotal = relatorio.getTempoTotal();
        this.mediaTempo = relatorio.getMediaTempo();
        this.totalResumos = relatorio.getTotalResumos();
        this.categoriaMaisConsumida = relatorio.getCategoriaMaisConsumida();
        this.diasParaConcluir = relatorio.getDiasParaConcluir();
        this.metaConcluida = relatorio.isMetaConcluida();
    }

    public RelatorioDTO(){

    }

    //Getters

    public double getTempoTotal() {
        return tempoTotal;
    }

    public double getMediaTempo() {
        return mediaTempo;
    }

    public int getTotalResumos() {
        return totalResumos;
    }

    public String getCategoriaMaisConsumida() {
        return categoriaMaisConsumida;
    }

    public int getDiasParaConcluir() {
        return diasParaConcluir;
    }

    public boolean isMetaConcluida() {
        return metaConcluida;
    }

    //Setters

    public void setTempoTotal(double tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

    public void setMediaTempo(double mediaTempo) {
        this.mediaTempo = mediaTempo;
    }

    public void setTotalResumos(int totalResumos) {
        this.totalResumos = totalResumos;
    }

    public void setCategoriaMaisConsumida(String categoriaMaisConsumida) {
        this.categoriaMaisConsumida = categoriaMaisConsumida;
    }

    public void setDiasParaConcluir(int diasParaConcluir) {
        this.diasParaConcluir = diasParaConcluir;
    }

    public void setMetaConcluida(boolean metaConcluida) {
        this.metaConcluida = metaConcluida;
    }
}
