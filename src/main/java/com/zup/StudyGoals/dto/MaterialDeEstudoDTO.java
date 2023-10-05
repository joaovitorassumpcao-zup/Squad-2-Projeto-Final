package com.zup.StudyGoals.dto;

import com.zup.StudyGoals.domain.Categoria;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

public @Data class MaterialDeEstudoDTO implements Serializable {

    @Setter(AccessLevel.NONE)
    Long id;

    String titulo;

    Categoria categoria;

    String url;

    String resumo;

    LocalDateTime dataInicio;

    LocalDateTime dataConclusao;

}
