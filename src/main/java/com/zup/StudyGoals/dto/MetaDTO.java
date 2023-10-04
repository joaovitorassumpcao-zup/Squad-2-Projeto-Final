package com.zup.StudyGoals.dto;

import com.zup.StudyGoals.domain.MaterialDeEstudo;
import com.zup.StudyGoals.domain.Meta;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public @Data class MetaDTO implements Serializable {

    @Setter(AccessLevel.NONE)
    Long id;

    String assunto;

    LocalDateTime dataDeInicio;

    LocalDateTime dataFinal;

    int metaMinutosDia;

    String objetivo;

    List<MaterialDeEstudo> materiaisDeEstudoList = new ArrayList<>();

    public MetaDTO(Meta meta) {
    }
}
