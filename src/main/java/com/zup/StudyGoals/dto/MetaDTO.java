package com.zup.StudyGoals.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


public class MetaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String assunto;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataDeInicio;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataFinal;

    private int metaMinutosDia;

    private String objetivo;

    private List<MaterialDeEstudo> MateriaisDeEstudo;

    public MetaDTO (Meta meta){
        this.assunto = meta.getAssunto();
        this.dataDeInicio = meta.getDataDeInicio();
        this.dataFinal = meta.getDataFinal();
        this.metaMinutosDia = meta.getMetaMinutosDia();
        this.objetivo = meta.getObjetivo();
        this.MateriaisDeEstudo = meta.getMateriaisDeEstudo();
    }

    public MetaDTO() {

    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public LocalDateTime getDataDeInicio() {
        return dataDeInicio;
    }

    public void setDataDeInicio(LocalDateTime dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public LocalDateTime getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDateTime dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getMetaMinutosDia() {
        return metaMinutosDia;
    }

    public void setMetaMinutosDia(int metaMinutosDia) {
        this.metaMinutosDia = metaMinutosDia;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public List<MaterialDeEstudo> getMateriaisDeEstudo() {
        return MateriaisDeEstudo;
    }

    public void setMateriaisDeEstudo(List<MaterialDeEstudo> materiaisDeEstudo) {
        MateriaisDeEstudo = materiaisDeEstudo;
    }
}
