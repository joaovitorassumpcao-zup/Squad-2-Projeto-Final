package com.zup.StudyGoals.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zup.StudyGoals.domain.Categoria;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;

import java.io.Serializable;
import java.time.LocalDateTime;


public class MaterialDeEstudoDTO implements Serializable {
    private Long id;
    private static final long serialVersionUID = 1L;
    private String titulo;
    private Categoria categoria;
    private String url;
    private String resumo;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataInicio;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataConclusao;

    public MaterialDeEstudoDTO (MaterialDeEstudo materialDeEstudo){
        this.id = materialDeEstudo.getId();
        this.titulo = materialDeEstudo.getTitulo();
        this.categoria = materialDeEstudo.getCategoria();
        this.url = materialDeEstudo.getUrl();
        this.resumo = materialDeEstudo.getResumo();
        this.dataInicio = materialDeEstudo.getDataInicio();
        this.dataConclusao = materialDeEstudo.getDataConclusao();
    }

    public MaterialDeEstudoDTO (){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    @Override
    public String toString() {
        return "MaterialDeEstudoDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", categoria=" + categoria +
                ", url='" + url + '\'' +
                ", resumo='" + resumo + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataConclusao=" + dataConclusao +
                '}';
    }
}
