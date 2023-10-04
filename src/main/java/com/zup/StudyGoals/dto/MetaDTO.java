package com.zup.StudyGoals.dto;

import com.zup.StudyGoals.domain.MaterialDeEstudo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public @Data class MetaDTO implements Serializable {

    @Setter(AccessLevel.NONE)
    Long id;

    String assunto;

    LocalDateTime dataDeInicio;

    LocalDateTime dataFinal;

    int metaMinutosDia;

    String objetivo;

    List<MaterialDeEstudo> materiaisDeEstudoList = new ArrayList<>();

}
