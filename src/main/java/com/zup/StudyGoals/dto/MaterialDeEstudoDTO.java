package com.zup.StudyGoals.dto;

import com.zup.StudyGoals.domain.Categoria;
import com.zup.StudyGoals.domain.MaterialDeEstudo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class MaterialDeEstudoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String titulo;
    private Categoria categoria;
    private String url;
    private String resumo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataConclusao;

    public MaterialDeEstudoDTO (MaterialDeEstudo materialDeEstudo){
        this.titulo = materialDeEstudo.getTitulo();
        this.categoria = materialDeEstudo.getCategoria();
        this.url = materialDeEstudo.getUrl();
        this.resumo = materialDeEstudo.getResumo();
        this.dataInicio = materialDeEstudo.getDataInicio();
        this.dataConclusao = materialDeEstudo.getDataConclusao();
    }

    public MaterialDeEstudoDTO (){

    }
}
