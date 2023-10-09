package com.zup.StudyGoals.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

public @Data class RelatorioDTO implements Serializable {

    @Setter(AccessLevel.NONE)
    Long id;

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

    public RelatorioDTO(){

    }
}
